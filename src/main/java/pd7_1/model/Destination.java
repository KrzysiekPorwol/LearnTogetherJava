package pd7_1.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Destination {
  private final String name;
  private final String country;

  public static Destination of(String name, String country) {
    if (StringUtils.isAnyBlank(name, country)) {
      throw new IllegalArgumentException("Destination name and country must not be blank");
    }
    return new Destination(name.trim(), country.trim());
  }
}
