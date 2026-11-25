//package pd15.mapper;
//
//import pd15.dto.CountrySearchResponse;
//import pd15.dto.CountrySearchResponse.CountryItem;
//import pd15.dto.CountrySearchResponse.CountryItem.Capital;
//import pd15.dto.CountrySearchResponse.CountryItem.Names;
//
//import java.util.List;
//
//class CountryMapperTest {
//
//  private static CountrySearchResponse responseWith(List<CountryItem> objects) {
//    return new CountrySearchResponse(new CountrySearchResponse.Data(objects));
//  }
//
//  private static CountryItem poland() {
//    return polandWith(
//        new Names("Poland", "Republic of Poland"),
//        List.of(new Capital("Warsaw")));
//  }
//
//  private static CountryItem polandWith(Names names, List<Capital> capitals) {
//    return new CountryItem(names, capitals, "Europe", 38_000_000L);
//  }
//
//  private static CountryItem germany() {
//    return new CountryItem(
//        new Names("Germany", "Federal Republic of Germany"),
//        List.of(new Capital("Berlin")),
//        "Europe",
//        83_000_000L);
//  }
//}
