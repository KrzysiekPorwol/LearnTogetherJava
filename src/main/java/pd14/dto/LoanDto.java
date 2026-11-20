package pd14.dto;

import pd14.domain.LoanStatus;

import java.time.LocalDate;

public record LoanDto(
    Long id,
    Long bookId,
    String bookTitle,
    Long memberId,
    String memberName,
    LocalDate borrowedAt,
    LocalDate dueDate,
    LocalDate returnedAt,
    LoanStatus status,
    boolean overdue
) {
}
