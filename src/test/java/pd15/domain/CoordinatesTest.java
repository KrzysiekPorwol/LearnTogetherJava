package pd15.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoordinatesTest {
  private static final Double VALID_LATITUDE = 52.23;
  private static final Double VALID_LONGITUDE = 21.01;

  @Test
  void shouldCreateCoordinatesWhenValuesAreValid() {
    Coordinates coordinates = new Coordinates(VALID_LATITUDE, VALID_LONGITUDE);

    assertThat(coordinates.latitude()).isEqualTo(VALID_LATITUDE);
    assertThat(coordinates.longitude()).isEqualTo(VALID_LONGITUDE);
  }

  @Test
  void shouldAcceptLatitudeEqualToMaxRange() {
    Coordinates coordinates = new Coordinates(90.0, VALID_LONGITUDE);

    assertThat(coordinates.latitude()).isEqualTo(90.0);
  }

  @Test
  void shouldThrowWhenLatitudeAboveMaxRange() {
    Double latitude = 90.0001;

    assertThatThrownBy(() -> new Coordinates(latitude, VALID_LONGITUDE))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("latitude");
  }

  @Test
  void shouldAcceptLatitudeEqualToMinRange() {
    Coordinates coordinates = new Coordinates(-90.0, VALID_LONGITUDE);

    assertThat(coordinates.latitude()).isEqualTo(-90.0);
  }

  @Test
  void shouldThrowWhenLatitudeBelowMinRange() {
    Double latitude = -90.0001;

    assertThatThrownBy(() -> new Coordinates(latitude, VALID_LONGITUDE))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("latitude");
  }

  @Test
  void shouldAcceptLongitudeEqualToMaxRange() {
    Coordinates coordinates = new Coordinates(VALID_LATITUDE, 180.0);

    assertThat(coordinates.longitude()).isEqualTo(180.0);
  }

  @Test
  void shouldThrowWhenLongitudeAboveMaxRange() {
    Double longitude = 180.0001;

    assertThatThrownBy(() -> new Coordinates(VALID_LATITUDE, longitude))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("longitude");
  }

  @Test
  void shouldAcceptLongitudeEqualToMinRange() {
    Coordinates coordinates = new Coordinates(VALID_LATITUDE, -180.0);

    assertThat(coordinates.longitude()).isEqualTo(-180.0);
  }

  @Test
  void shouldThrowWhenLongitudeBelowMinRange() {
    Double longitude = -180.0001;

    assertThatThrownBy(() -> new Coordinates(VALID_LATITUDE, longitude))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("longitude");
  }

  @Test
  void shouldThrowWhenLatitudeIsNull() {
    assertThatThrownBy(() -> new Coordinates(null, VALID_LONGITUDE))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("latitude cannot be null");
  }

  @Test
  void shouldThrowWhenLongitudeIsNull() {
    assertThatThrownBy(() -> new Coordinates(VALID_LATITUDE, null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("longitude cannot be null");
  }
}
