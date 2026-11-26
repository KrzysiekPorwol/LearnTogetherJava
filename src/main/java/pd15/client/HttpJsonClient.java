package pd15.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pd15.exception.ExternalApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@RequiredArgsConstructor
public class HttpJsonClient {

  private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);
  private static final int HTTP_OK = 200;

  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  HttpResponse<String> get(String url) {
    HttpRequest request = baseRequest(url).build();

    return send(request, url);
  }

  HttpResponse<String> getWithBearerToken(String url, String token) {
    HttpRequest request = baseRequest(url)
        .header("Authorization", "Bearer " + token)
        .build();

    return send(request, url);
  }

  private HttpRequest.Builder baseRequest(String url) {
    return HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(REQUEST_TIMEOUT)
        .header("Accept", "application/json")
        .GET();
  }

  private HttpResponse<String> send(HttpRequest request, String url) {
    try {
      return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      throw new ExternalApiException(url, e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ExternalApiException(url, e);
    }
  }

  <T> T parseBody(HttpResponse<String> response, String url, TypeReference<T> type) {
    try {
      return objectMapper.readValue(response.body(), type);
    } catch (IOException e) {
      throw new ExternalApiException(url, e);
    }
  }

  void requireSuccessStatus(HttpResponse<String> response, String url) {
    if (response.statusCode() != HTTP_OK) {
      throw new ExternalApiException(url, response.statusCode());
    }
  }
}
