package pd14.repository;

import pd14.domain.Loan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoanRepository {

  private final Map<Long, Loan> loans = new HashMap<>();
  private long nextId = 1L;

  public Loan save(Loan loan) {
    if (loan == null) {
      throw new IllegalArgumentException("Loan cannot be null");
    }
    if (loan.getId() == null) {
      loan.assignId(nextId++);
    }
    loans.put(loan.getId(), loan);
    return loan;
  }

  public Optional<Loan> findById(Long id) {
    return Optional.ofNullable(loans.get(id));
  }

  public List<Loan> findAll() {
    return List.copyOf(loans.values());
  }
}
