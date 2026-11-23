package pd15.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd15.client.JsonPlaceholderClient;
import pd15.domain.UserReport;
import pd15.exception.UserReportException;
import pd15.service.UserReportService;
import pd15.service.impl.UserReportServiceImpl;

import java.net.http.HttpClient;
import java.time.Duration;

public class ServiceTest {
  public static void main(String[] args) {
    HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5))
        .build();
    ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    JsonPlaceholderClient client =
        new JsonPlaceholderClient(httpClient, objectMapper, "https://jsonplaceholder.typicode.com");
    UserReportService gateway = new UserReportServiceImpl(client);

    System.out.println("1) raport dla usera 1:");
    UserReport report = gateway.getUserReport(1L);
    System.out.println("user:  " + report.user());
    System.out.println("todos: " + report.todos().size() + " niewykonanych");
    report.todos().forEach(todo -> System.out.println("       " + todo));
    System.out.println("posts: " + report.posts());

    System.out.println("2) nieistniejący user:");
    try {
      gateway.getUserReport(999L);
    } catch (UserReportException e) {
      System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
  }
}
