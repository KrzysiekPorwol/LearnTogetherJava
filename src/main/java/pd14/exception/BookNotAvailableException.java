package pd14.exception;

public class BookNotAvailableException extends LibraryException {
  public BookNotAvailableException(String title) {
    super("No available copies of book: " + title);
  }
}
