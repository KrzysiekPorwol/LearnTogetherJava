package pd15;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd15.client.HttpJsonClient;
import pd15.client.JsonPlaceholderClient;
import pd15.client.OpenMeteoClient;
import pd15.client.RestCountriesClient;
import pd15.domain.UserReport;
import pd15.service.UserReportService;
import pd15.service.impl.UserReportServiceImpl;

import java.net.http.HttpClient;
import java.time.Duration;

public class App {

  private static final String JSON_PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com";
  private static final String OPEN_METEO_URL = "https://api.open-meteo.com";
  private static final String REST_COUNTRIES_URL = "https://api.restcountries.com/countries/v5";
  //GDZIE ZAPISAC TAKI KLUCZ?
  private static final String REST_COUNTRIES_API_KEY = "rc_live_demo";
  private static final String REPORT_COUNTRY = "Poland";

  public static void main(String[] args) {
    HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5))
        .build();
    ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    HttpJsonClient httpJsonClient = new HttpJsonClient(httpClient, objectMapper);

    UserReportService userReportService = new UserReportServiceImpl(
        new JsonPlaceholderClient(httpJsonClient, JSON_PLACEHOLDER_URL),
        new OpenMeteoClient(httpJsonClient, OPEN_METEO_URL),
        new RestCountriesClient(httpJsonClient, REST_COUNTRIES_URL, REST_COUNTRIES_API_KEY),
        REPORT_COUNTRY);

    UserReport report = userReportService.getUserReport(1L);

    System.out.println("user:    " + report.user());
    System.out.println("todos:   " + report.todos().size() + " incomplete");
    System.out.println("posts:   " + report.posts());
    System.out.println("weather: " + report.weather());
    System.out.println("country: " + report.country());
  }
}