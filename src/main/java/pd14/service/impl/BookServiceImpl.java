package pd14.service.impl;

import lombok.RequiredArgsConstructor;
import pd14.domain.Book;
import pd14.dto.BookDto;
import pd14.dto.CreateBookCommand;
import pd14.exception.BookAlreadyExistsException;
import pd14.exception.BookNotFoundException;
import pd14.mapper.BookMapper;
import pd14.repository.BookRepository;
import pd14.service.BookService;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  public BookDto addBook(CreateBookCommand request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }
    if (bookRepository.existsByIsbn(request.isbn())) {
      throw new BookAlreadyExistsException(request.isbn());
    }

    Book book = Book.of(request.isbn(), request.title(), request.author(),
        request.year(), request.copies());
    Book saved = bookRepository.save(book);
    return BookMapper.toDto(saved);
  }

  @Override
  public BookDto findByIsbn(String isbn) {
    return bookRepository.findByIsbn(isbn)
        .map(BookMapper::toDto)
        .orElseThrow(() -> BookNotFoundException.byIsbn(isbn));
  }

  @Override
  public List<BookDto> findAvailable() {
    return bookRepository.findAll().stream()
        .filter(Book::isAvailable)
        .sorted(Comparator.comparing(Book::getTitle))
        .map(BookMapper::toDto)
        .toList();
  }

  @Override
  public List<BookDto> search(String query) {
    if (query == null || query.isBlank()) {
      throw new IllegalArgumentException("Search query cannot be empty");
    }
    String normalizedQuery = query.strip().toLowerCase();

    return bookRepository.findAll().stream()
        .filter(book -> matches(book, normalizedQuery))
        .sorted(Comparator.comparing(Book::getTitle))
        .map(BookMapper::toDto)
        .toList();
  }

  private boolean matches(Book book, String normalizedQuery) {
    return book.getTitle().toLowerCase().contains(normalizedQuery)
        || book.getAuthor().toLowerCase().contains(normalizedQuery);
  }
}
