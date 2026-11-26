package pd15.domain;

public record User(Long id, String name, String email, String city, Coordinates coordinates) {
}
