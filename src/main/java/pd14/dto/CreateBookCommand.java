package pd14.dto;

public record CreateBookCommand(
    String isbn,
    String title,
    String author,
    int year,
    int copies
) {
}
