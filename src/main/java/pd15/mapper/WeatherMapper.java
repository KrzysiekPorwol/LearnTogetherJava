package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Weather;
import pd15.dto.CurrentWeatherResponse;
import pd15.dto.WeatherResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WeatherMapper {

  public static Optional<Weather> toDomain(WeatherResponse weatherResponse) {
    return Optional.ofNullable(weatherResponse.current())
        .map(WeatherMapper::toWeather);
  }

  private static Weather toWeather(CurrentWeatherResponse current) {
    LocalDateTime time = Optional.ofNullable(current.time())
        .map(LocalDateTime::parse)
        .orElse(null);

    return new Weather(time, current.temperature(), current.rain());
  }
}
