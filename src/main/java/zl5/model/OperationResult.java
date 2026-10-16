package zl5.model;

public class OperationResult<T> {
  private final boolean success;
  private final String message;
  private final T value;

  private OperationResult(boolean success, String message, T value) {
    this.success = success;
    this.message = message;
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public static <T> OperationResult<T> success(T value) {
    return new OperationResult<>(true, "udało się", value);
  }

  public static <T> OperationResult<T> failure(String message) {
    return new OperationResult<>(false, message, null);
  }
}