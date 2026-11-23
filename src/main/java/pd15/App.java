package pd15;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd15.dto.UserResponse;

public class App {

}


//https://api.open-meteo.com/v1/forecast?latitude=-37.3159&longitude=81.1496&current=temperature_2m,rain&timezone=auto


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