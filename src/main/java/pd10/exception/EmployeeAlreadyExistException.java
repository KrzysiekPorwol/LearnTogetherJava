package pd10.exception;

public class EmployeeAlreadyExistException extends RuntimeException {
  public EmployeeAlreadyExistException(String identification, String value) {

    super("Employee with %s %s already exists".formatted(identification, value));
  }
}