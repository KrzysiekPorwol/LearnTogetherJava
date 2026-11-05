package pd11.service;

import lombok.RequiredArgsConstructor;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReportService {

  private final SaleRepository saleRepository;

  public BigDecimal totalRevenue() {
    return saleRepository
        .findAll()
        .stream()
        .map(Sale::revenue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
