package pd14.service;

import pd14.dto.BookDto;
import pd14.dto.CreateBookRequest;

import java.util.List;

public interface BookService {

  BookDto addBook(CreateBookRequest request);

  BookDto findByIsbn(String isbn);

  List<BookDto> findAvailable();

  List<BookDto> search(String query);
}
