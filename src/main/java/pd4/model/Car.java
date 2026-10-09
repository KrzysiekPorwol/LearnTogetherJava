package pd4.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Car extends Resource {

  private static final int LONG_TERM_MIN_DAYS = 7;
  private static final BigDecimal LONG_TERM_DISCOUNT_RATE = new BigDecimal("0.10");
  private final String model;

  public Car(String name, BigDecimal pricePerDay, String model) {
    super(name, ResourceType.CAR, pricePerDay);
    this.model = model;
  }

  @Override
  public BigDecimal calculateRentalCost(int days) {
    BigDecimal baseCost = getPricePerDay().multiply(BigDecimal.valueOf(days));
    return baseCost.subtract(calculateDiscount(days, baseCost)).setScale(2, RoundingMode.HALF_UP);
  }

  private BigDecimal calculateDiscount(int days, BigDecimal baseCost) {
    if (days < LONG_TERM_MIN_DAYS) {
      return BigDecimal.ZERO;
    }
    return baseCost.multiply(LONG_TERM_DISCOUNT_RATE);
  }

  @Override
  public String toString() {
    return super.toString() + " | Model: " + model;
  }
}
