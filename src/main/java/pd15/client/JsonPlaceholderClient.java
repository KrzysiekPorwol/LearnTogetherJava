package pd15.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pd15.dto.PostResponse;
import pd15.dto.TodoResponse;
import pd15.dto.UserResponse;
import pd15.exception.ExternalApiException;
import pd15.exception.UserNotFoundException;
import pd15.validation.Validator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
public class JsonPlaceholderClient {

  private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);
  private static final int HTTP_OK = 200;
  private static final int HTTP_NOT_FOUND = 404;

  private static final TypeReference<UserResponse> USER_TYPE = new TypeReference<>() {};
  private static final TypeReference<List<TodoResponse>> TODO_LIST_TYPE = new TypeReference<>() {};
  private static final TypeReference<List<PostResponse>> POST_LIST_TYPE = new TypeReference<>() {};

  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;
  private final String baseUrl;

  public UserResponse fetchUser(Long userId) {
    Validator.requireNonNull(userId, "userId");
    String url = baseUrl + "/users/" + userId;

    HttpResponse<String> response = get(url);
    if (response.statusCode() == HTTP_NOT_FOUND) {
      throw new UserNotFoundException(userId);
    }
    return readBody(response, url, USER_TYPE);
  }

  public List<TodoResponse> fetchTodos(Long userId) {
    Validator.requireNonNull(userId, "userId");
    String url = baseUrl + "/users/" + userId + "/todos";

    return readBody(get(url), url, TODO_LIST_TYPE);
  }

  public List<PostResponse> fetchPosts(Long userId) {
    Validator.requireNonNull(userId, "userId");
    String url = baseUrl + "/users/" + userId + "/posts";

    return readBody(get(url), url, POST_LIST_TYPE);
  }

  private HttpResponse<String> get(String url) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(REQUEST_TIMEOUT)
        .header("Accept", "application/json")
        .GET()
        .build();

    try {
      return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      throw new ExternalApiException(url, e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ExternalApiException(url, e);
    }
  }

  private <T> T readBody(HttpResponse<String> response, String url, TypeReference<T> type) {
    if (response.statusCode() != HTTP_OK) {
      throw new ExternalApiException(url, response.statusCode());
    }
    try {
      return objectMapper.readValue(response.body(), type);
    } catch (IOException e) {
      throw new ExternalApiException(url, e);
    }
  }
}
