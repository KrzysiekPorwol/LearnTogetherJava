package pd14.dto;

public record ReturnBookRequest(Long loanId) {

  public ReturnBookRequest {
    if (loanId == null) {
      throw new IllegalArgumentException("loanId is required");
    }
  }
}
