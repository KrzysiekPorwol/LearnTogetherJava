package pd11.model;

import org.apache.commons.lang3.StringUtils;
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
    Objects.requireNonNull(date, "date nie może być null");
    Objects.requireNonNull(unitPrice, "unitPrice nie może być null");

    Validate.notBlank(productId, "productId nie może być pusty");
    Validate.notBlank(productName, "productName nie może być pusty");
    Validate.notBlank(category, "category nie może być pusta");

    if (quantity <= 0) {
      throw new IllegalArgumentException("Ilość sztuk mniejsza bądź równa 0");
    }
    if (unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Cena jednostkowa nie może być mniejsza bądź równa 0");
    }
  }
}