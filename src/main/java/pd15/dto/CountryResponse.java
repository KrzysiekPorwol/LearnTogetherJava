package pd15.dto;

import java.util.List;

public record CountryResponse(
    CountryNamesResponse names,
    List<CapitalResponse> capitals,
    String region,
    Long population
) {
}
