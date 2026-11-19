package pd14.mapper;

import pd14.domain.Book;
import pd14.dto.BookDto;

public final class BookMapper {

  private BookMapper() {
  }

  public static BookDto toDto(Book book) {
    return new BookDto(
        book.getId(),
        book.getIsbn(),
        book.getTitle(),
        book.getAuthor(),
        book.getYear(),
        book.getAvailableCopies()
    );
  }
}
