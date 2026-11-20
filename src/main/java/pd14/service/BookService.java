package pd14.service;

import pd14.dto.BookDto;
import pd14.dto.CreateBookCommand;

import java.util.List;

public interface BookService {

  BookDto addBook(CreateBookCommand request);

  BookDto findByIsbn(String isbn);

  List<BookDto> findAvailable();

  List<BookDto> search(String query);
}
