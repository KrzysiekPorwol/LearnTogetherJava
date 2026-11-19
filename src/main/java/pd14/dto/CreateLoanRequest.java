package pd14.dto;

public record CreateLoanRequest(Long memberId, Long bookId) {

  public CreateLoanRequest {
    if (memberId == null) {
      throw new IllegalArgumentException("memberId is required");
    }
    if (bookId == null) {
      throw new IllegalArgumentException("bookId is required");
    }
  }
}
