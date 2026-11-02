package pd10.exception;

public class EmployeeAlreadyExistException extends RuntimeException {
  public EmployeeAlreadyExistException(String message) {
    super(message);
  }
}