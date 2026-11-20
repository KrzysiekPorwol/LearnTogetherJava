package pd14.exception;

public class LoanLimitExceededException extends LibraryException {
  public LoanLimitExceededException(String memberName, int limit) {
    super("Member " + memberName + " has reached the limit of " + limit + " active loans");
  }
}
