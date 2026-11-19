package pd14.service;

import lombok.RequiredArgsConstructor;
import pd14.domain.Book;
import pd14.domain.Loan;
import pd14.domain.Member;
import pd14.dto.CreateLoanRequest;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookRequest;
import pd14.exception.BookNotFoundException;
import pd14.exception.LoanLimitExceededException;
import pd14.exception.LoanNotFoundException;
import pd14.exception.MemberNotFoundException;
import pd14.mapper.LoanMapper;
import pd14.repository.BookRepository;
import pd14.repository.LoanRepository;
import pd14.repository.MemberRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

  private final LoanRepository loanRepository;
  private final MemberRepository memberRepository;
  private final BookRepository bookRepository;
  private final Clock clock;

  @Override
  public LoanDto borrow(CreateLoanRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }
    Member member = requireMember(request.memberId());
    Book book = requireBook(request.bookId());

    if (!member.canBorrow()) {
      throw new LoanLimitExceededException(member.getName(), Member.MAX_ACTIVE_LOANS);
    }
    book.borrow();

    Loan loan = Loan.create(book, member, LocalDate.now(clock));
    member.addLoan(loan);
    Loan saved = loanRepository.save(loan);
    return LoanMapper.toDto(saved);
  }

  @Override
  public LoanDto returnBook(ReturnBookRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }
    Loan loan = requireLoan(request.loanId());

    loan.markReturned(LocalDate.now(clock));
    loan.getBook().returnBack();
    return LoanMapper.toDto(loan);
  }

  @Override
  public List<LoanDto> findOverdue() {
    return loanRepository.findAll().stream()
        .filter(Loan::isOverdue)
        .sorted(Comparator.comparing(Loan::getDueDate))
        .map(LoanMapper::toDto)
        .toList();
  }

  private Member requireMember(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException(id));
  }

  private Book requireBook(Long id) {
    return bookRepository.findById(id)
        .orElseThrow(() -> BookNotFoundException.byId(id));
  }

  private Loan requireLoan(Long id) {
    return loanRepository.findById(id)
        .orElseThrow(() -> new LoanNotFoundException(id));
  }
}
