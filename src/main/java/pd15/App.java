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
  private static final String REST_COUNTRIES_API_KEY_ENV = "REST_COUNTRIES_API_KEY";
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
        new RestCountriesClient(httpJsonClient, REST_COUNTRIES_URL, System.getenv(REST_COUNTRIES_API_KEY_ENV)),
        REPORT_COUNTRY);

    UserReport report = userReportService.getUserReport(1L);

    System.out.println("user:    " + report.user());
    System.out.println("todos:   " + report.todos().size() + " incomplete");
    System.out.println("posts:   " + report.posts());
    System.out.println("weather: " + report.weather());
    System.out.println("country: " + report.country());
  }
}

//PRACA DOMOWA PD-15 — API Gateway do zewnętrznych serwisów
//
//Zbuduj "agregator danych" który pobiera informacje z 3 publicznych API (bez klucza):
//
//JSONPlaceholder (https://jsonplaceholder.typicode.com/):
//    GET /users/{id}  — dane użytkownika
//GET /users/{id}/todos — lista zadań
//GET /users/{id}/posts — lista postów
//
//Open-Meteo (https://api.open-meteo.com/) — pogoda dla lokalizacji użytkownika
//
//    REST Countries (https://api.restcountries.com/countries/v5) — dane o kraju
//
//    Wymagania:
//    a) Klasa ApiGateway z metodą getUserReport(Long userId) → UserReport
//b) UserReport = user + todos (tylko incomplete) + posts (ostatnie 3) + pogoda
//c) Pobierz todos + posts + pogodę RÓWNOLEGLE przez sendAsync + CompletableFuture.allOf
//d) Obsłuż błędy każdego API niezależnie — jeden failed API nie psuje całości
//e) Zmierz czas: pobieranie sekwencyjne vs równoległe (Instant.now())
