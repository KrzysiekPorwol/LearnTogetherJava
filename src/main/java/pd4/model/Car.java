package pd4.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Car extends Resource {

  private static final int LONG_TERM_MIN_DAYS = 7;
  private static final BigDecimal LONG_TERM_DISCOUNT = new BigDecimal("0.10");

  private final String model;

  public Car(String name, BigDecimal pricePerDay, String model) {
    super(name, ResourceType.CAR, pricePerDay);
    this.model = model;
  }

  @Override
  public BigDecimal calculateRentalCost(int days) {
    BigDecimal cost = getPricePerDay().multiply(BigDecimal.valueOf(days));
    if (days >= LONG_TERM_MIN_DAYS) {
      cost = cost.multiply(BigDecimal.ONE.subtract(LONG_TERM_DISCOUNT));
    }
    return cost.setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public String toString() {
    return super.toString() + " | Model: " + model;
  }
}
