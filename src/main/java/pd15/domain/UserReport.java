package pd15.domain;

import pd15.validation.Validator;

import java.util.List;

public record UserReport(User user, List<Todo> todos, List<Post> posts, Weather weather, Country country) {

  public UserReport {
    Validator.requireNonNull(user, "user");
    todos = List.copyOf(Validator.requireNonNull(todos, "todos"));
    posts = List.copyOf(Validator.requireNonNull(posts, "posts"));
  }
}
