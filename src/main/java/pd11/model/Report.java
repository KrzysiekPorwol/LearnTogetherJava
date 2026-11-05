package pd11.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public record Report(
    BigDecimal totalRevenue,
    Map<String, BigDecimal> topProductsByRevenue,
    Map<String, BigDecimal> revenueByCategory,
    Map<String, BigDecimal> averageUnitPriceByCategory,
    LocalDate bestSalesDay
) {
}
