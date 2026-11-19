package pd14;

import pd14.dto.BookDto;
import pd14.dto.CreateBookRequest;
import pd14.dto.CreateLoanRequest;
import pd14.dto.LoanDto;
import pd14.dto.MemberDto;
import pd14.dto.RegisterMemberRequest;
import pd14.dto.ReturnBookRequest;
import pd14.exception.LibraryException;
import pd14.repository.BookRepository;
import pd14.repository.LoanRepository;
import pd14.repository.MemberRepository;
import pd14.service.BookService;
import pd14.service.BookServiceImpl;
import pd14.service.LoanService;
import pd14.service.LoanServiceImpl;
import pd14.service.MemberService;
import pd14.service.MemberServiceImpl;

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
        new CreateBookRequest("978-83-7578-063-5", "Wiedzmin", "Andrzej Sapkowski", 1993, 1));
    MemberDto jan = memberService.register(new RegisterMemberRequest("Jan Kowalski", "jan@mail.pl"));
    MemberDto anna = memberService.register(new RegisterMemberRequest("Anna Nowak", "anna@mail.pl"));
    System.out.println("Dodano: " + witcher);
    System.out.println("Zarejestrowano: " + jan + ", " + anna);

    System.out.println("\n1: wypożyczenie i zwrot");

    LoanDto loan = loanService.borrow(new CreateLoanRequest(jan.id(), witcher.id()));
    System.out.println("Wypożyczono: " + loan);

    System.out.println("Aktywne wypożyczenia Jana: " + memberService.getActiveLoans(jan.id()));
    System.out.println("Dostępne książki: " + bookService.findAvailable());

    LoanDto returned = loanService.returnBook(new ReturnBookRequest(loan.id()));
    System.out.println("Zwrócono: " + returned);
    System.out.println("Dostępne książki: " + bookService.findAvailable());

    System.out.println("\n2: brak wolnych kopii");

    loanService.borrow(new CreateLoanRequest(jan.id(), witcher.id()));
    try {
      loanService.borrow(new CreateLoanRequest(anna.id(), witcher.id()));
    } catch (LibraryException e) {
      System.out.println("Nie udało się wypożyczyć: " + e.getMessage());
    }
  }
}