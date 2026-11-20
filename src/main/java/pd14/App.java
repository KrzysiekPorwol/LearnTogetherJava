package pd14;

import pd14.dto.BookDto;
import pd14.dto.CreateBookCommand;
import pd14.dto.CreateLoanCommand;
import pd14.dto.LoanDto;
import pd14.dto.MemberDto;
import pd14.dto.RegisterMemberCommand;
import pd14.dto.ReturnBookCommand;
import pd14.exception.LibraryException;
import pd14.repository.BookRepository;
import pd14.repository.LoanRepository;
import pd14.repository.MemberRepository;
import pd14.service.BookService;
import pd14.service.impl.BookServiceImpl;
import pd14.service.LoanService;
import pd14.service.impl.LoanServiceImpl;
import pd14.service.MemberService;
import pd14.service.impl.MemberServiceImpl;

import java.time.Clock;

public class App {

  public static void main(String[] args) {

    BookRepository bookRepository = new BookRepository();
    MemberRepository memberRepository = new MemberRepository();
    LoanRepository loanRepository = new LoanRepository();

    BookService bookService = new BookServiceImpl(bookRepository);
    MemberService memberService = new MemberServiceImpl(memberRepository);
    LoanService loanService = new LoanServiceImpl(
        loanRepository, memberRepository, bookRepository, Clock.systemDefaultZone());

    BookDto witcher = bookService.addBook(
        new CreateBookCommand("978-83-7578-063-5", "Wiedzmin", "Andrzej Sapkowski", 1993, 1));
    MemberDto jan = memberService.register(new RegisterMemberCommand("Jan Kowalski", "jan@mail.pl"));
    MemberDto anna = memberService.register(new RegisterMemberCommand("Anna Nowak", "anna@mail.pl"));
    System.out.println("Dodano: " + witcher);
    System.out.println("Zarejestrowano: " + jan + ", " + anna);

    System.out.println("\n1: wypożyczenie i zwrot");

    LoanDto loan = loanService.borrow(new CreateLoanCommand(jan.id(), witcher.id()));
    System.out.println("Wypożyczono: " + loan);

    System.out.println("Aktywne wypożyczenia Jana: " + memberService.getActiveLoans(jan.id()));
    System.out.println("Dostępne książki: " + bookService.findAvailable());

    LoanDto returned = loanService.returnBook(new ReturnBookCommand(loan.id()));
    System.out.println("Zwrócono: " + returned);
    System.out.println("Dostępne książki: " + bookService.findAvailable());

    System.out.println("\n2: brak wolnych kopii");

    loanService.borrow(new CreateLoanCommand(jan.id(), witcher.id()));
    try {
      loanService.borrow(new CreateLoanCommand(anna.id(), witcher.id()));
    } catch (LibraryException e) {
      System.out.println("Nie udało się wypożyczyć: " + e.getMessage());
    }
  }
}