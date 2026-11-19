package pd14.domain;

import lombok.Getter;
import pd14.exception.LoanLimitExceededException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Member {

  public static final int MAX_ACTIVE_LOANS = 3;

  private Long id;
  private final String name;
  private final String email;
  private final List<Loan> loans = new ArrayList<>();

  private Member(String name, String email) {
    this.name = name;
    this.email = email;
  }

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

  public void addLoan(Loan loan) {
    if (loan == null) {
      throw new IllegalArgumentException("Loan cannot be null");
    }
    if (loan.getMember() != this) {
      throw new IllegalArgumentException("Loan belongs to another member");
    }
    if (!canBorrow()) {
      throw new LoanLimitExceededException(name, MAX_ACTIVE_LOANS);
    }
    loans.add(loan);
  }

  public boolean canBorrow() {
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
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Member member = (Member) o;
    return Objects.equals(id, member.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Member #%s: %s (%s), active loans: %d"
        .formatted(id, name, email, getActiveLoans().size());
  }
}
