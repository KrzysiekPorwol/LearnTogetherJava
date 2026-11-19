package pd14.mapper;

import pd14.domain.Loan;
import pd14.dto.LoanDto;

public final class LoanMapper {

  private LoanMapper() {
  }

  public static LoanDto toDto(Loan loan) {
    return new LoanDto(
        loan.getId(),
        loan.getBook().getId(),
        loan.getBook().getTitle(),
        loan.getMember().getId(),
        loan.getMember().getName(),
        loan.getBorrowedAt(),
        loan.getDueDate(),
        loan.getReturnedAt(),
        loan.isOverdue()
    );
  }
}
