package pd14.exception;

public class LoanNotFoundException extends LibraryException {
  public LoanNotFoundException(Long id) {
    super("Loan with id " + id + " not found");
  }
}
