package pd14.domain;

import lombok.Getter;
import pd14.exception.BookNotAvailableException;

import java.time.Year;
import java.util.Objects;

@Getter
public class Book {

  private Long id;
  private final String isbn;
  private final String title;
  private final String author;
  private final int year;
  private int availableCopies;

  private Book(String isbn, String title, String author, int year, int availableCopies) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.year = year;
    this.availableCopies = availableCopies;
  }

  public static Book create(String isbn, String title, String author, int year, int availableCopies) {
    if (isbn == null || isbn.isBlank()) {
      throw new IllegalArgumentException("ISBN cannot be empty");
    }
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }
    if (author == null || author.isBlank()) {
      throw new IllegalArgumentException("Author cannot be empty");
    }
    if (year <= 0 || year > Year.now().getValue()) {
      throw new IllegalArgumentException("Year must be between 1 and current year");
    }
    if (availableCopies < 0) {
      throw new IllegalArgumentException("Available copies cannot be negative");
    }
    return new Book(isbn, title, author, year, availableCopies);
  }

  public void assignId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id cannot be null");
    }
    if (this.id != null) {
      throw new IllegalStateException("this book already has an ID");
    }
    this.id = id;
  }

  public void borrow() {
    if (!isAvailable()) {
      throw new BookNotAvailableException(title);
    }
    availableCopies--;
  }

  public void returnBack() {
    availableCopies++;
  }

  public boolean isAvailable() {
    return availableCopies > 0;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(id, book.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Book #%s: \"%s\" by %s (%d), ISBN: %s, available: %d"
        .formatted(id, title, author, year, isbn, availableCopies);
  }
}
