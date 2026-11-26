package pd15.client;

import com.fasterxml.jackson.core.type.TypeReference;
import pd15.dto.CountrySearchResponse;
import pd15.exception.MissingApiKeyException;
import pd15.validation.Validator;

import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class RestCountriesClient {

  private static final String API_NAME = "REST Countries";
  private static final TypeReference<CountrySearchResponse> COUNTRY_SEARCH_TYPE = new TypeReference<>() {};

  private final HttpJsonClient httpJsonClient;
  private final String baseUrl;
  private final String apiKey;

  public RestCountriesClient(HttpJsonClient httpJsonClient, String baseUrl, String apiKey) {
    this.httpJsonClient = Validator.requireNonNull(httpJsonClient, "httpJsonClient");
    this.baseUrl = Validator.requireNonBlank(baseUrl, "baseUrl");
    this.apiKey = apiKey;
  }

  public CountrySearchResponse fetchCountryByName(String countryName) {
    Validator.requireNonBlank(countryName, "countryName");
    if (apiKey == null || apiKey.isBlank()) {
      throw new MissingApiKeyException(API_NAME);
    }
    String url = baseUrl + "/names.common/" + encodePathSegment(countryName);

    HttpResponse<String> httpResponse = httpJsonClient.getWithBearerToken(url, apiKey);

    httpJsonClient.requireSuccessStatus(httpResponse, url);

    return httpJsonClient.parseBody(httpResponse, url, COUNTRY_SEARCH_TYPE);
  }

  private static String encodePathSegment(String value) {
    return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20");
  }
}
