package pd14.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Loan {

  private static final int LOAN_PERIOD_DAYS = 14;

  private Long id;
  private final Book book;
  private final Member member;
  private final LocalDate borrowedAt;
  private final LocalDate dueDate;
  private LocalDate returnedAt;

  private Loan(Book book, Member member, LocalDate borrowedAt) {
    this.book = book;
    this.member = member;
    this.borrowedAt = borrowedAt;
    this.dueDate = borrowedAt.plusDays(LOAN_PERIOD_DAYS);
  }

  public static Loan create(Book book, Member member, LocalDate borrowedAt) {
    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null");
    }
    if (member == null) {
      throw new IllegalArgumentException("Member cannot be null");
    }
    if (borrowedAt == null) {
      throw new IllegalArgumentException("Borrow date cannot be null");
    }
    return new Loan(book, member, borrowedAt);
  }

  public void assignId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id cannot be null");
    }
    if (this.id != null) {
      throw new IllegalStateException("this loan already has an ID");
    }
    this.id = id;
  }

  public void markReturned(LocalDate returnDate) {
    if (returnDate == null) {
      throw new IllegalArgumentException("Return date cannot be null");
    }
    if (!isActive()) {
      throw new IllegalStateException("Loan #" + id + " is already returned");
    }
    if (returnDate.isBefore(borrowedAt)) {
      throw new IllegalArgumentException("Return date cannot be before borrow date");
    }
    this.returnedAt = returnDate;
  }

  public boolean isActive() {
    return returnedAt == null;
  }

  public boolean isOverdue() {
    return isActive() && dueDate.isBefore(LocalDate.now());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Loan loan = (Loan) o;
    return Objects.equals(id, loan.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Loan #%s: \"%s\" -> %s, borrowed: %s, due: %s, returned: %s"
        .formatted(id, book.getTitle(), member.getName(), borrowedAt, dueDate,
            returnedAt != null ? returnedAt : "-");
  }
}
