package pd4.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Skis extends Resource {

  private static final BigDecimal SERVICE_FEE = new BigDecimal("20.00");

  private final int lengthInCm;

  public Skis(String name, BigDecimal pricePerDay, int lengthInCm) {
    super(name, ResourceType.SKI, pricePerDay);
    this.lengthInCm = lengthInCm;
  }

  @Override
  public BigDecimal calculateRentalCost(int days) {
    return getPricePerDay()
          .multiply(BigDecimal.valueOf(days))
          .add(SERVICE_FEE)
          .setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public String toString() {
    return super.toString() + " | LengthCm: " + lengthInCm;
  }
}
