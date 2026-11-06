package pd11.model;

import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public record Sale(
    LocalDate date,
    String productId,
    String productName,
    String category,
    int quantity,
    BigDecimal unitPrice
) {

  public Sale {
    Objects.requireNonNull(date, "date must not be null");
    Objects.requireNonNull(unitPrice, "unitPrice must not be null");

    Validate.notBlank(productId, "productId must not be blank");
    Validate.notBlank(productName, "productName must not be blank");
    Validate.notBlank(category, "category must not be blank");

    if (quantity <= 0) {
      throw new IllegalArgumentException("quantity must be greater than 0");
    }
    if (unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("unitPrice must be greater than 0");
    }
  }

  public BigDecimal revenue() {
    return unitPrice.multiply(BigDecimal.valueOf(quantity));
  }
}
