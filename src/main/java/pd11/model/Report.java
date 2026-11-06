package pd11.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public record Report(
    BigDecimal totalRevenue,
    List<String> topProductsByRevenue,
    Map<String, BigDecimal> revenueByCategory,
    Map<String, BigDecimal> averageUnitPriceByCategory,
    LocalDate bestSalesDay
) {
}
