package pd14.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pd14.exception.LoanLimitExceededException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Member {

  public static final int MAX_ACTIVE_LOANS = 3;
  @EqualsAndHashCode.Include
  private Long id;
  private final String name;
  private final String email;
  private final List<Loan> loans = new ArrayList<>();

  public static Member create(String name, String email) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    if (email == null || !email.contains("@")) {
      throw new IllegalArgumentException("Invalid email: " + email);
    }
    return new Member(name, email);
  }

  public void assignId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id cannot be null");
    }
    if (this.id != null) {
      throw new IllegalStateException("this member already has an ID");
    }
    this.id = id;
  }

  public Loan borrow(Book book, LocalDate borrowedAt) {
    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null");
    }
    if (!isAvailableForLoan()) {
      throw new LoanLimitExceededException(name, MAX_ACTIVE_LOANS);
    }
    Loan loan = Loan.create(book, this, borrowedAt);
    book.borrow();
    loans.add(loan);
    return loan;
  }

  public boolean isAvailableForLoan() {
    return getActiveLoans().size() < MAX_ACTIVE_LOANS;
  }

  public List<Loan> getActiveLoans() {
    return loans.stream()
        .filter(Loan::isActive)
        .toList();
  }

  public List<Loan> getLoans() {
    return List.copyOf(loans);
  }

  @Override
  public String toString() {
    return "Member #%s: %s (%s), active loans: %d"
        .formatted(id, name, email, getActiveLoans().size());
  }
}
