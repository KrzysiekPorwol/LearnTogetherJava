package pd15.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponse(Current current) {

  public record Current(
      String time,
      @JsonProperty("temperature_2m") Double temperature,
      Double rain
  ) {
  }
}
