package pd4.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
  private final List<Rental> rentals = new ArrayList<>();

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public List<Rental> getRentals() {
    return new ArrayList<>(rentals);
  }

  public BigDecimal calculateTotalCost() {
    BigDecimal totalCost = BigDecimal.ZERO;
    for (Rental rental : rentals) {
      totalCost = totalCost.add(rental.calculateCost());
    }
    return totalCost;
  }

  public int countRentalsByStatus(RentalStatus status) {
    int count = 0;
    for (Rental rental : rentals) {
      if (rental.getStatus() == status) {
        count++;
      }
    }
    return count;
  }

  public void printAllRentals() {
    for (Rental rental : rentals) {
      System.out.println(rental);
    }
  }
}


//Utwórz klasę reprezentującą system wypożyczeń
//Powinna:
//przechowywać wiele wypożyczeń
//pozwalać dodać nowe wypożyczenie
//obliczać łączny koszt wszystkich wypożyczeń
//zwracać liczbę wypożyczeń o wskazanym statusie