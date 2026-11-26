package pd15.client;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import pd15.dto.PostResponse;
import pd15.dto.TodoResponse;
import pd15.dto.UserResponse;
import pd15.exception.UserNotFoundException;
import pd15.validation.Validator;

import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
public class JsonPlaceholderClient {

  private static final int HTTP_NOT_FOUND = 404;

  private static final TypeReference<UserResponse> USER_TYPE = new TypeReference<>() {};
  private static final TypeReference<List<TodoResponse>> TODO_LIST_TYPE = new TypeReference<>() {};
  private static final TypeReference<List<PostResponse>> POST_LIST_TYPE = new TypeReference<>() {};

  private final HttpJsonClient httpJsonClient;
  private final String baseUrl;

  public UserResponse fetchUser(Long userId) {
    Validator.requireNonNull(userId, "userId");
    String url = baseUrl + "/users/" + userId;

    HttpResponse<String> httpResponse = httpJsonClient.get(url);
    if (httpResponse.statusCode() == HTTP_NOT_FOUND) {
      throw new UserNotFoundException(userId);
    }
    httpJsonClient.requireSuccessStatus(httpResponse, url);

    return httpJsonClient.parseBody(httpResponse, url, USER_TYPE);
  }

  public List<TodoResponse> fetchTodos(Long userId) {
    Validator.requireNonNull(userId, "userId");
    String url = baseUrl + "/users/" + userId + "/todos";

    HttpResponse<String> httpResponse = httpJsonClient.get(url);

    httpJsonClient.requireSuccessStatus(httpResponse, url);

    return httpJsonClient.parseBody(httpResponse, url, TODO_LIST_TYPE);
  }

  public List<PostResponse> fetchPosts(Long userId) {
    Validator.requireNonNull(userId, "userId");
    String url = baseUrl + "/users/" + userId + "/posts";

    HttpResponse<String> httpResponse = httpJsonClient.get(url);

    httpJsonClient.requireSuccessStatus(httpResponse, url);

    return httpJsonClient.parseBody(httpResponse, url, POST_LIST_TYPE);
  }
}
