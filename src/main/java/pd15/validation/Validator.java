package pd15.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Validator {

  public static <T> T requireNonNull(T value, String fieldName) {
    if (value == null) {
      throw new IllegalArgumentException(fieldName + " cannot be null");
    }
    return value;
  }

  public static String requireNonBlank(String value, String fieldName) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
    return value;
  }

  public static Double requireInRange(Double value, double min, double max, String fieldName) {
    requireNonNull(value, fieldName);
    if (value < min || value > max) {
      throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max);
    }
    return value;
  }
}
