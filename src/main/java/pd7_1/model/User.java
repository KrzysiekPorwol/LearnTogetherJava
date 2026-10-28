package pd7_1.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class User {

  private final String name;
  private final TransportType preferredTransport;
  private final Double budget;

  public static User of(String name, TransportType preferredTransport, Double budget) {
    if (StringUtils.isBlank(name)) {
      throw new IllegalArgumentException("User name must not be blank");
    }
    return new User(name.trim(), preferredTransport, budget);
  }
}