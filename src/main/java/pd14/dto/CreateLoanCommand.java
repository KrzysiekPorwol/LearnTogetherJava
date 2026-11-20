package pd14.dto;

public record CreateLoanCommand(Long memberId, Long bookId) {

  public CreateLoanCommand {
    if (memberId == null) {
      throw new IllegalArgumentException("memberId is required");
    }
    if (bookId == null) {
      throw new IllegalArgumentException("bookId is required");
    }
  }
}
