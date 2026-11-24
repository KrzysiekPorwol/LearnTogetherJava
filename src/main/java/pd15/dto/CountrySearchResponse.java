package pd15.dto;

import java.util.List;

public record CountrySearchResponse(Data data) {

  public record Data(List<CountryItem> objects) {
  }

  public record CountryItem(
      Names names,
      List<Capital> capitals,
      String region,
      Long population
  ) {

    public record Names(String common, String official) {
    }

    public record Capital(String name) {
    }
  }
}
