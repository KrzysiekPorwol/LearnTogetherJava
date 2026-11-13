package pd11.service;

import lombok.RequiredArgsConstructor;
import pd11.model.Report;
import pd11.model.Sale;
import pd11.repository.SaleRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReportService {

  private static final int TOP_PRODUCTS_LIMIT = 3;
  private static final int PRICE_SCALE = 2;

  private final SaleRepository saleRepository;

  private BigDecimal totalRevenue() {
    return saleRepository
        .findAll()
        .stream()
        .map(Sale::revenue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private List<String> topProductsByRevenue() {
    Map<String, BigDecimal> revenueByProduct = saleRepository
        .findAll()
        .stream()
        .collect(Collectors.toMap(Sale::productName, Sale::revenue, BigDecimal::add));

    return revenueByProduct
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(TOP_PRODUCTS_LIMIT)
        .map(Map.Entry::getKey)
        .toList();
  }

  private Map<String, BigDecimal> revenueByCategory() {
    return saleRepository
        .findAll()
        .stream()
        .collect(Collectors.toMap(Sale::category, Sale::revenue, BigDecimal::add))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (first, second) -> first,
            LinkedHashMap::new
        ));
  }

  private Map<String, BigDecimal> averageUnitPriceByCategory() {
    return saleRepository
        .findAll()
        .stream()
        .collect(Collectors.groupingBy(
            Sale::category,
            Collectors.collectingAndThen(Collectors.toList(), this::averageUnitPrice)
        ));
  }

  private Optional<LocalDate> bestSalesDay() {
    Map<LocalDate, BigDecimal> revenueByDay = saleRepository
        .findAll()
        .stream()
        .collect(Collectors.toMap(Sale::date, Sale::revenue, BigDecimal::add));

    return revenueByDay
        .entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey);
  }

  public Report generateReport() {
    BigDecimal totalRevenue = totalRevenue();
    List<String> topProductsByRevenue = topProductsByRevenue();
    Map<String, BigDecimal> revenueByCategory = revenueByCategory();
    Map<String, BigDecimal> averageUnitPriceByCategory = averageUnitPriceByCategory();
    LocalDate bestSalesDay = bestSalesDay().orElse(null);

    return new Report(
        totalRevenue,
        topProductsByRevenue,
        revenueByCategory,
        averageUnitPriceByCategory,
        bestSalesDay
    );
  }

  private BigDecimal averageUnitPrice(List<Sale> sales) {
    BigDecimal unitPriceSum = sales
        .stream()
        .map(Sale::unitPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return unitPriceSum.divide(BigDecimal.valueOf(sales.size()), PRICE_SCALE, RoundingMode.HALF_UP);
  }
}
