package pd14.dto;

public record ReturnBookCommand(Long loanId) {

  public ReturnBookCommand {
    if (loanId == null) {
      throw new IllegalArgumentException("loanId is required");
    }
  }
}
