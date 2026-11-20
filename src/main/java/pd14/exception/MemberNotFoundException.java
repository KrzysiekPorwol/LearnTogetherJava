package pd14.exception;

public class MemberNotFoundException extends LibraryException {
  public MemberNotFoundException(Long id) {
    super("Member with id " + id + " not found");
  }
}
