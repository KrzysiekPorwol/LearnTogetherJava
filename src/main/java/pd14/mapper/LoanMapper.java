package pd14.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd14.domain.Loan;
import pd14.dto.LoanDto;
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class LoanMapper {

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
        loan.getStatus(),
        loan.isOverdue()
    );
  }
}
