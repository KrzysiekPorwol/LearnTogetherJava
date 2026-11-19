package pd14.dto;

public record CreateBookRequest(
    String isbn,
    String title,
    String author,
    int year,
    int copies
) {
}
