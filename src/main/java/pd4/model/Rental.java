package pd4.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Rental {
  private final Resource resource;
  private final int leaseTermInDays;
  @Setter
  private RentalStatus status;

  public static Rental create(Resource resource, int leaseTermInDays) {
    if (resource == null) {
      throw new NullPointerException("Probujesz zrobić wypożyczenie na zasobie null");
    }
    if (leaseTermInDays <= 0) {
      throw new IllegalArgumentException("Probujesz stworzyć wypozyczenie które ma termin mniej niz jeden dzien");
    }
    return new Rental(resource, leaseTermInDays, RentalStatus.PENDING);
  }

  public BigDecimal calculateCost() {
    return resource.calculateRentalCost(leaseTermInDays);
  }

  @Override
  public String toString() {
    return resource + " | Lease Term In Days: " + leaseTermInDays + " | Rental status: " + status;
  }
}