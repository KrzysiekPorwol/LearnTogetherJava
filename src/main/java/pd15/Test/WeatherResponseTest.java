package pd15.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd15.dto.WeatherResponse;

public class WeatherResponseTest {
  public static void main(String[] args) throws JsonProcessingException {
    // Prawdziwa odpowiedź z:
    // GET https://api.open-meteo.com/v1/forecast?latitude=-37.3159&longitude=81.1496&current=temperature_2m,rain&timezone=auto
    String json = """
        {
            "latitude": -37.2935,
            "longitude": 81.16711,
            "generationtime_ms": 0.05364418029785156,
            "utc_offset_seconds": 18000,
            "timezone": "Etc/GMT-5",
            "timezone_abbreviation": "GMT+5",
            "elevation": 0.0,
            "current_units": {
                "time": "iso8601",
                "interval": "seconds",
                "temperature_2m": "°C",
                "rain": "mm"
            },
            "current": {
                "time": "2026-11-23T10:15",
                "interval": 900,
                "temperature_2m": 12.8,
                "rain": 0.0
            }
        }
        """;

    // Przypadek brzegowy: brak pola "rain" i "temperature_2m": null
    String jsonWithMissingValues = """
        {
            "current": {
                "time": "2026-11-23T10:15",
                "interval": 900,
                "temperature_2m": null
            }
        }
        """;

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    WeatherResponse weather = objectMapper.readValue(json, WeatherResponse.class);
    WeatherResponse weatherWithMissingValues = objectMapper.readValue(jsonWithMissingValues, WeatherResponse.class);
    System.out.println(weather);
    System.out.println(weatherWithMissingValues);
  }
}
