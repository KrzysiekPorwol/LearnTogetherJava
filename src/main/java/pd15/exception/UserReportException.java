package pd15.exception;

public abstract class UserReportException extends RuntimeException {

  protected UserReportException(String message) {
    super(message);
  }

  protected UserReportException(String message, Throwable cause) {
    super(message, cause);
  }
}
