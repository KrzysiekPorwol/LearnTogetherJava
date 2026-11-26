package pd15.exception;

public class MissingApiKeyException extends UserReportException {

  public MissingApiKeyException(String apiName) {
    super("API key for " + apiName + " is not configured");
  }
}
