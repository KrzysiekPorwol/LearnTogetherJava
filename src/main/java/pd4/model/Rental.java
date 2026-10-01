package pd4.model;

import java.math.BigDecimal;

public class Rental {
  private final Resource resource;
  private final int leaseTermInDays;
  private RentalStatus status;

  public Rental(Resource resource, int leaseTermInDays) {
    this.resource = resource;
    this.leaseTermInDays = leaseTermInDays;
    this.status = RentalStatus.PENDING;
  }

  public Resource getResource() {
    return resource;
  }

  public int getLeaseTermInDays() {
    return leaseTermInDays;
  }

  public RentalStatus getStatus() {
    return status;
  }

  public void setStatus(RentalStatus status) {
    this.status = status;
  }

  public BigDecimal calculateCost() {
    return resource.calculateRentalCost(leaseTermInDays);
  }

  @Override
  public String toString() {
    return resource + " | Lease Term In Days: " + leaseTermInDays + " | Rental status: " + status;
  }
}