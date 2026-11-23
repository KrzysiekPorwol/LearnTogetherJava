package pd15.service.impl;

import lombok.RequiredArgsConstructor;
import pd15.client.JsonPlaceholderClient;
import pd15.domain.Post;
import pd15.domain.Todo;
import pd15.domain.User;
import pd15.domain.UserReport;
import pd15.dto.PostResponse;
import pd15.dto.TodoResponse;
import pd15.exception.UserReportException;
import pd15.mapper.PostMapper;
import pd15.mapper.TodoMapper;
import pd15.mapper.UserMapper;
import pd15.service.UserReportService;
import pd15.validation.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class UserReportServiceImpl implements UserReportService {

  private static final System.Logger LOGGER = System.getLogger(UserReportServiceImpl.class.getName());
  private static final int LATEST_POSTS_LIMIT = 3;

  private final JsonPlaceholderClient jsonPlaceholderClient;

  @Override
  public UserReport getUserReport(Long userId) {
    Validator.requireNonNull(userId, "userId");

    User user = UserMapper.toDomain(jsonPlaceholderClient.fetchUser(userId));

    List<Todo> todos = fetchOrEmpty("todos",
        () -> toIncompleteTodos(jsonPlaceholderClient.fetchTodos(userId)));

    List<Post> posts = fetchOrEmpty("posts",
        () -> toLatestPosts(jsonPlaceholderClient.fetchPosts(userId)));

    return new UserReport(user, todos, posts, null, null);
  }

  private List<Todo> toIncompleteTodos(List<TodoResponse> todoResponses) {
    return todoResponses.stream()
        .filter(Objects::nonNull)
        .filter(todo -> Boolean.FALSE.equals(todo.completed()))
        .map(TodoMapper::toDomain)
        .toList();
  }

  private List<Post> toLatestPosts(List<PostResponse> postResponses) {
    return postResponses.stream()
        .filter(Objects::nonNull)
        .filter(post -> post.id() != null)
        .sorted(Comparator.comparing(PostResponse::id, Comparator.reverseOrder()))
        .limit(LATEST_POSTS_LIMIT)
        .map(PostMapper::toDomain)
        .toList();
  }

  private <T> List<T> fetchOrEmpty(String partName, Supplier<List<T>> fetch) {
    try {
      return fetch.get();
    } catch (UserReportException e) {
      LOGGER.log(System.Logger.Level.WARNING, "Could not fetch " + partName + ": " + e.getMessage());
      return List.of();
    }
  }
}
