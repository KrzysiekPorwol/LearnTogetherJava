package pd15.service.impl;

import pd15.client.JsonPlaceholderClient;
import pd15.client.OpenMeteoClient;
import pd15.client.RestCountriesClient;
import pd15.domain.Coordinates;
import pd15.domain.Country;
import pd15.domain.Post;
import pd15.domain.Todo;
import pd15.domain.User;
import pd15.domain.UserReport;
import pd15.domain.Weather;
import pd15.dto.PostResponse;
import pd15.dto.TodoResponse;
import pd15.exception.UserReportException;
import pd15.mapper.CountryMapper;
import pd15.mapper.PostMapper;
import pd15.mapper.TodoMapper;
import pd15.mapper.UserMapper;
import pd15.mapper.WeatherMapper;
import pd15.service.UserReportService;
import pd15.validation.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class UserReportServiceImpl implements UserReportService {

  private static final System.Logger LOGGER = System.getLogger(UserReportServiceImpl.class.getName());
  private static final int LATEST_POSTS_LIMIT = 3;

  private final JsonPlaceholderClient jsonPlaceholderClient;
  private final OpenMeteoClient openMeteoClient;
  private final RestCountriesClient restCountriesClient;
  private final String countryName;

  public UserReportServiceImpl(JsonPlaceholderClient jsonPlaceholderClient,
                               OpenMeteoClient openMeteoClient,
                               RestCountriesClient restCountriesClient,
                               String countryName) {
    this.jsonPlaceholderClient = Validator.requireNonNull(jsonPlaceholderClient, "jsonPlaceholderClient");
    this.openMeteoClient = Validator.requireNonNull(openMeteoClient, "openMeteoClient");
    this.restCountriesClient = Validator.requireNonNull(restCountriesClient, "restCountriesClient");
    this.countryName = Validator.requireNonBlank(countryName, "countryName");
  }

  @Override
  public UserReport getUserReport(Long userId) {
    Validator.requireNonNull(userId, "userId");

    User user = UserMapper.toDomain(jsonPlaceholderClient.fetchUser(userId));

    List<Todo> todos = fetchOrFallback("todos",
        () -> toIncompleteTodos(jsonPlaceholderClient.fetchTodos(userId)),
        List.of());

    List<Post> posts = fetchOrFallback("posts",
        () -> toLatestPosts(jsonPlaceholderClient.fetchPosts(userId)),
        List.of());

    Optional<Weather> weather = fetchOrFallback("weather",
        () -> fetchWeather(user),
        Optional.empty());

    Optional<Country> country = fetchOrFallback("country",
        () -> CountryMapper.toDomain(restCountriesClient.fetchCountryByName(countryName)),
        Optional.empty());

    return new UserReport(user, todos, posts, weather.orElse(null), country.orElse(null));
  }

  private Optional<Weather> fetchWeather(User user) {
    Coordinates coordinates = user.coordinates();
    if (coordinates == null) {
      LOGGER.log(System.Logger.Level.INFO, "Skipping weather: user " + user.id() + " has no coordinates");
      return Optional.empty();
    }
    return WeatherMapper.toDomain(
        openMeteoClient.fetchCurrentWeather(coordinates.latitude(), coordinates.longitude()));
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

  private <T> T fetchOrFallback(String partName, Supplier<T> fetch, T fallback) {
    try {
      return fetch.get();
    } catch (UserReportException e) {
      LOGGER.log(System.Logger.Level.WARNING, "Could not fetch " + partName + ": " + e.getMessage());
      return fallback;
    }
  }
}
