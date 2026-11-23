package pd15.exception;

public class UserNotFoundException extends UserReportException {

  public UserNotFoundException(Long id) {
    super("User with id " + id + " not found");
  }
}
