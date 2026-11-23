package pd15.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentWeatherResponse(
    String time,
    @JsonProperty("temperature_2m") Double temperature,
    Double rain
) {
}
