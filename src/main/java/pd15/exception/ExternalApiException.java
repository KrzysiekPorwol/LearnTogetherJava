package pd15.exception;

public class ExternalApiException extends UserReportException {

  public ExternalApiException(String url, int statusCode) {
    super("Request to " + url + " failed with status " + statusCode);
  }

  public ExternalApiException(String url, Throwable cause) {
    super("Request to " + url + " failed: " + cause, cause);
  }
}
