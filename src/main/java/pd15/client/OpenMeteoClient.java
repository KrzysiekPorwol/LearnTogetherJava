package pd15.client;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import pd15.dto.WeatherResponse;
import pd15.validation.Validator;

import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class OpenMeteoClient {

  private static final TypeReference<WeatherResponse> WEATHER_TYPE = new TypeReference<>() {};
  private static final String CURRENT_VARIABLES = "temperature_2m,rain";

  private final HttpJsonClient httpJsonClient;
  private final String baseUrl;

  public WeatherResponse fetchCurrentWeather(Double latitude, Double longitude) {
    Validator.requireInRange(latitude, -90, 90, "latitude");
    Validator.requireInRange(longitude, -180, 180, "longitude");
    String url = baseUrl + "/v1/forecast"
        + "?latitude=" + latitude
        + "&longitude=" + longitude
        + "&current=" + CURRENT_VARIABLES
        + "&timezone=auto";

    HttpResponse<String> httpResponse = httpJsonClient.get(url);

    httpJsonClient.requireSuccessStatus(httpResponse, url);

    return httpJsonClient.parseBody(httpResponse, url, WEATHER_TYPE);
  }
}
