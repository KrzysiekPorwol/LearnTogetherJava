package pd14.exception;

public class BookNotFoundException extends LibraryException {

  private BookNotFoundException(String message) {
    super(message);
  }

  public static BookNotFoundException byId(Long id) {
    return new BookNotFoundException("Book with id " + id + " not found");
  }

  public static BookNotFoundException byIsbn(String isbn) {
    return new BookNotFoundException("Book with ISBN " + isbn + " not found");
  }
}
