package pd15.domain;

import pd15.validation.Validator;

public record Coordinates(Double latitude, Double longitude) {

  public Coordinates {
    Validator.requireInRange(latitude, -90, 90, "latitude");
    Validator.requireInRange(longitude, -180, 180, "longitude");
  }
}
