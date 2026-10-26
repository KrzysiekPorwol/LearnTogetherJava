package pd7_1.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Destination {
  private final String name;
  private final String country;

  public static Destination of(String name, String country) {
    return new Destination(name.trim(), country.trim());
  }
}
