package pd14.repository;

import pd14.domain.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookRepository {

  private final Map<Long, Book> books = new HashMap<>();
  private long nextId = 1L;

  public Book save(Book book) {
    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null");
    }
    if (book.getId() == null) {
      book.assignId(nextId++);
    }
    books.put(book.getId(), book);
    return book;
  }

  public Optional<Book> findById(Long id) {
    return Optional.ofNullable(books.get(id));
  }

  public Optional<Book> findByIsbn(String isbn) {
    return books.values().stream()
        .filter(book -> book.getIsbn().equals(isbn))
        .findFirst();
  }

  public List<Book> findAll() {
    return List.copyOf(books.values());
  }
}
