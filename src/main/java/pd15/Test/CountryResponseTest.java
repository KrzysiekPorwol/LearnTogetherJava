package pd15.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd15.dto.CountrySearchResponse;

public class CountryResponseTest {
  public static void main(String[] args) throws JsonProcessingException {
    String json = """
        {
            "data": {
                "_demo": {
                    "message": "You just ran a test against the demo key.",
                    "signup_url": "https://restcountries.com/sign-up"
                },
                "objects": [
                    {
                        "names": {
                            "alternates": [],
                            "common": "Canada",
                            "native": {
                                "eng": { "common": "Canada", "official": "Canada" },
                                "fra": { "common": "Canada", "official": "Canada" }
                            },
                            "official": "Canada"
                        },
                        "codes": {
                            "alpha_2": "CA",
                            "alpha_3": "CAN"
                        },
                        "capitals": [
                            {
                                "attributes": {
                                    "administrative": false,
                                    "constitutional": false,
                                    "executive": false,
                                    "judicial": false,
                                    "legislative": false,
                                    "primary": true
                                },
                                "coordinates": { "lat": 45.42, "lng": -75.7 },
                                "name": "Ottawa"
                            }
                        ],
                        "region": "Americas",
                        "subregion": "North America",
                        "population": 41798407,
                        "currencies": [
                            { "code": "CAD", "name": "Canadian dollar", "symbol": "$" }
                        ],
                        "coordinates": { "lat": 60, "lng": -95 }
                    }
                ],
                "meta": {
                    "total": 1,
                    "count": 1,
                    "limit": 25,
                    "offset": 0,
                    "more": false
                }
            }
        }
        """;

    String jsonNotFound = """
        {
            "data": {
                "objects": [],
                "meta": { "total": 0, "count": 0, "limit": 25, "offset": 0, "more": false }
            }
        }
        """;

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    CountrySearchResponse country = objectMapper.readValue(json, CountrySearchResponse.class);
    CountrySearchResponse notFound = objectMapper.readValue(jsonNotFound, CountrySearchResponse.class);
    System.out.println(country);
    System.out.println(notFound);
  }
}
