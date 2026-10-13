package pd4.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Comparator;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Resource implements Comparable<Resource> {

  public static final Comparator<Resource> BY_NAME = Comparator.comparing(Resource::getName);

  @Getter
  private final String name;
  private final ResourceType resourceType;
  @Getter
  private final BigDecimal pricePerDay;

  public abstract BigDecimal calculateRentalCost(int days);

  @Override
  public String toString() {
    return "Name: " + name + " | ResourceType: " + resourceType + " | Price per day: " + pricePerDay;
  }

  @Override
  public int compareTo(Resource other) {
    return this.pricePerDay.compareTo(other.pricePerDay);
  }
}