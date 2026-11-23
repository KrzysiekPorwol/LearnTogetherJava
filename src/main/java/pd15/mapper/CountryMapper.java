package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Country;
import pd15.dto.CapitalResponse;
import pd15.dto.CountryDataResponse;
import pd15.dto.CountryNamesResponse;
import pd15.dto.CountryResponse;
import pd15.dto.CountrySearchResponse;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CountryMapper {

  public static Optional<Country> toDomain(CountrySearchResponse countrySearchResponse) {
    return Optional.ofNullable(countrySearchResponse.data())
        .map(CountryDataResponse::objects)
        .flatMap(objects -> objects.stream()
            .filter(Objects::nonNull)
            .findFirst())
        .map(CountryMapper::toCountry);
  }

  private static Country toCountry(CountryResponse countryResponse) {
    String name = Optional.ofNullable(countryResponse.names())
        .map(CountryNamesResponse::common)
        .orElse(null);

    String capital = Optional.ofNullable(countryResponse.capitals())
        .flatMap(capitals -> capitals.stream()
            .filter(Objects::nonNull)
            .findFirst())
        .map(CapitalResponse::name)
        .orElse(null);

    return new Country(name, capital, countryResponse.region(), countryResponse.population());
  }
}
