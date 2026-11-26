package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Coordinates;
import pd15.domain.User;
import pd15.dto.UserResponse;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

  public static User toDomain(UserResponse userResponse) {
    Optional<UserResponse.Address> address = Optional.ofNullable(userResponse.address());

    String city = address
        .map(UserResponse.Address::city)
        .orElse(null);

    Coordinates coordinates = address
        .map(UserResponse.Address::geo)
        .flatMap(UserMapper::toCoordinates)
        .orElse(null);

    return new User(userResponse.id(), userResponse.name(), userResponse.email(), city, coordinates);
  }

  private static Optional<Coordinates> toCoordinates(UserResponse.Address.Geo geo) {
    if (geo.lat() == null || geo.lng() == null) {
      return Optional.empty();
    }
    try {
      return Optional.of(new Coordinates(Double.valueOf(geo.lat()), Double.valueOf(geo.lng())));
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
  }
}
