package pd4.model;

import java.math.BigDecimal;
import java.util.Comparator;

public abstract class Resource implements Comparable<Resource> {

  public static final Comparator<Resource> BY_NAME = Comparator.comparing(resource -> resource.name);

  private final int id;
  private final String name;
  private final ResourceType resourceType;
  private final BigDecimal pricePerDay;
  private static int nextId = 1;

  protected Resource(String name, ResourceType resourceType, BigDecimal pricePerDay) {
    this.id = nextId++;
    this.name = name;
    this.resourceType = resourceType;
    this.pricePerDay = pricePerDay;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPricePerDay() {
    return pricePerDay;
  }

  public abstract BigDecimal calculateRentalCost(int days);

  @Override
  public String toString() {
    return "ID: " + id + " | Name: " + name + " | ResourceType: " + resourceType + " | Price per day: " + pricePerDay;
  }

  @Override
  public int compareTo(Resource other) {
    return this.pricePerDay.compareTo(other.pricePerDay);
  }
}