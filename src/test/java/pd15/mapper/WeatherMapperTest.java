package pd15.mapper;

import org.junit.jupiter.api.Test;
import pd15.domain.Weather;
import pd15.dto.WeatherResponse;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherMapperTest {
  @Test
  void shouldMapAllFieldsWhenCurrentWeatherIsPresent() {
    WeatherResponse response = new WeatherResponse(
        new WeatherResponse.Current("2026-11-24T14:00", 7.5, 0.2));

    Optional<Weather> result = WeatherMapper.toDomain(response);

    assertThat(result).contains(
        new Weather(LocalDateTime.of(2026, 11, 24, 14, 0), 7.5, 0.2));
  }

  @Test
  void shouldReturnEmptyWhenCurrentWeatherIsMissing() {
    WeatherResponse response = new WeatherResponse(null);

    Optional<Weather> result = WeatherMapper.toDomain(response);

    assertThat(result).isEmpty();
  }

  @Test
  void shouldMapTimeToNullWhenTimeIsMissing() {
    WeatherResponse response = new WeatherResponse(
        new WeatherResponse.Current(null, 7.5, 0.2));

    Optional<Weather> result = WeatherMapper.toDomain(response);

    assertThat(result).isPresent();
    assertThat(result.get().time()).isNull();
    assertThat(result.get().temperatureCelsius()).isEqualTo(7.5);
  }

  @Test
  void shouldKeepNullMeasurementsWhenApiDoesNotReturnThem() {
    WeatherResponse response = new WeatherResponse(
        new WeatherResponse.Current("2026-11-24T14:00", null, null));

    Optional<Weather> result = WeatherMapper.toDomain(response);

    assertThat(result).hasValueSatisfying(weather -> {
      assertThat(weather.temperatureCelsius()).isNull();
      assertThat(weather.rainMm()).isNull();
    });
  }
}
