package pd15.dto;

public record UserResponse(Long id, String name, String email, Address address) {

  public record Address(String city, Geo geo) {

    public record Geo(String lat, String lng) {
    }
  }
}
