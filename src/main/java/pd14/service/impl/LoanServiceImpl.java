package pd14.service.impl;

import lombok.RequiredArgsConstructor;
import pd14.domain.Book;
import pd14.domain.Loan;
import pd14.domain.Member;
import pd14.dto.CreateLoanCommand;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookCommand;
import pd14.exception.BookNotFoundException;
import pd14.exception.LoanNotFoundException;
import pd14.exception.MemberNotFoundException;
import pd14.mapper.LoanMapper;
import pd14.repository.BookRepository;
import pd14.repository.LoanRepository;
import pd14.repository.MemberRepository;
import pd14.service.LoanService;

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
  public LoanDto borrow(CreateLoanCommand request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }
    Member member = requireMember(request.memberId());
    Book book = requireBook(request.bookId());

    Loan loan = member.borrow(book, LocalDate.now(clock));
    Loan saved = loanRepository.save(loan);
    return LoanMapper.toDto(saved);
  }

  @Override
  public LoanDto returnBook(ReturnBookCommand request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }
    Loan loan = requireLoan(request.loanId());

    loan.markReturned(LocalDate.now(clock));
    loan.getBook().returnCopy();
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
