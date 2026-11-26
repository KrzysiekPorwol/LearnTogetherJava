package pd15.client;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pd15.dto.WeatherResponse;
import pd15.exception.ExternalApiException;

import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenMeteoClientTest {

  private static final String BASE_URL = "https://api.test";
  private static final String EXPECTED_URL = BASE_URL + "/v1/forecast"
      + "?latitude=52.23&longitude=21.01&current=temperature_2m,rain&timezone=auto";

  @Mock
  private HttpJsonClient httpJsonClient;

  @Mock
  private HttpResponse<String> httpResponse;

  private OpenMeteoClient openMeteoClient;

  @BeforeEach
  void setUp() {
    openMeteoClient = new OpenMeteoClient(httpJsonClient, BASE_URL);
  }

  @Test
  void shouldReturnParsedWeatherWhenResponseIsSuccessful() {
    WeatherResponse expected = new WeatherResponse(
        new WeatherResponse.Current("2026-11-24T14:00", 7.5, 0.2));
    when(httpJsonClient.get(EXPECTED_URL)).thenReturn(httpResponse);
    when(httpJsonClient.parseBody(any(), anyString(), any(TypeReference.class))).thenReturn(expected);

    WeatherResponse result = openMeteoClient.fetchCurrentWeather(52.23, 21.01);

    assertThat(result).isEqualTo(expected);
    verify(httpJsonClient).requireSuccessStatus(httpResponse, EXPECTED_URL);
  }

  @Test
  void shouldNotParseBodyWhenStatusIsNotSuccessful() {
    when(httpJsonClient.get(EXPECTED_URL)).thenReturn(httpResponse);
    doThrow(new ExternalApiException(EXPECTED_URL, 500))
        .when(httpJsonClient).requireSuccessStatus(httpResponse, EXPECTED_URL);

    assertThatThrownBy(() -> openMeteoClient.fetchCurrentWeather(52.23, 21.01))
        .isInstanceOf(ExternalApiException.class)
        .hasMessageContaining("500");

    verify(httpJsonClient, never()).parseBody(any(), anyString(), any(TypeReference.class));
  }

  @Test
  void shouldNotCallApiWhenLatitudeIsOutOfRange() {
    assertThatThrownBy(() -> openMeteoClient.fetchCurrentWeather(91.0, 21.01))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("latitude");

    verifyNoInteractions(httpJsonClient);
  }
}
