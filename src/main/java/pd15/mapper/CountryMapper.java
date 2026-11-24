package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Country;
import pd15.dto.CountrySearchResponse;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CountryMapper {

  public static Optional<Country> toDomain(CountrySearchResponse countrySearchResponse) {
    return Optional.ofNullable(countrySearchResponse.data())
        .map(CountrySearchResponse.Data::objects)
        .flatMap(objects -> objects.stream()
            .filter(Objects::nonNull)
            .findFirst())
        .map(CountryMapper::toCountry);
  }

  private static Country toCountry(CountrySearchResponse.CountryItem countryItem) {
    String name = Optional.ofNullable(countryItem.names())
        .map(CountrySearchResponse.CountryItem.Names::common)
        .orElse(null);

    String capital = Optional.ofNullable(countryItem.capitals())
        .flatMap(capitals -> capitals.stream()
            .filter(Objects::nonNull)
            .findFirst())
        .map(CountrySearchResponse.CountryItem.Capital::name)
        .orElse(null);

    return new Country(name, capital, countryItem.region(), countryItem.population());
  }
}
