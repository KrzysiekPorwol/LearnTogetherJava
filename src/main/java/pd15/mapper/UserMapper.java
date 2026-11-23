package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.User;
import pd15.dto.AddressResponse;
import pd15.dto.UserResponse;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

  public static User toDomain(UserResponse userResponse) {
    String city = Optional.ofNullable(userResponse.address())
        .map(AddressResponse::city)
        .orElse(null);

    return new User(userResponse.id(), userResponse.name(), userResponse.email(), city);
  }
}
