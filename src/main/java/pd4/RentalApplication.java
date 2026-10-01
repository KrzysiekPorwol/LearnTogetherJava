package pd4;

import pd4.model.*;

import java.math.BigDecimal;
import java.util.*;

public class RentalApplication {

  public static void main(String[] args) {


    RentalSystem rentalSystem = new RentalSystem();

    // Tworzenie zasobów które będą wypożyczane:
    Car car2 = new Car("BMW", new BigDecimal("300.00"), "e90");
    Car car1 = new Car("Alfa romeo", new BigDecimal("100.00"), "159");

    Skis skis1 = new Skis("Fursony", new BigDecimal("25.00"), 43);
    Skis skis2 = new Skis("Magma", new BigDecimal("30.00"), 45);

    List<Resource> resources = new ArrayList<>(List.of(car2, car1, skis1, skis2));


    System.out.println("Sortowanie po cenie:");
    Collections.sort(resources);

    printResources(resources);

    System.out.println("Sortowanie po nazwie:");
    Collections.sort(resources, Resource.BY_NAME);

    printResources(resources);


    // Rezerwacja zasobu do wypożyczenia:
    Rental rental2 = new Rental(car2, 14);
    Rental rental1 = new Rental(car1, 7);
    Rental rental4 = new Rental(skis2, 1);
    Rental rental3 = new Rental(skis1, 10);

    // Przypisanie wypożyczeń do systemu wypożyczeń:
    rentalSystem.addRental(rental1);
    rentalSystem.addRental(rental2);
    rentalSystem.addRental(rental3);
    rentalSystem.addRental(rental4);

    // Zmiana statusów, żeby podsumowanie pokazało różne stany:
    rental2.setStatus(RentalStatus.ACTIVE);
    rental4.setStatus(RentalStatus.RETURNED);

    printSummary(rentalSystem);

    // W aplikacji nie użyłem żadnego rekordu, ponieważ uważam
    // że nie nadają się one do moich klas.
  }

  private static void printResources(List<Resource> resources) {
    for (Resource resource : resources) {
      System.out.println(resource);
    }
  }

  private static void printSummary(RentalSystem rentalSystem) {
    System.out.println();
    System.out.println("=== Podsumowanie systemu ===");

    for (Rental rental : rentalSystem.getRentals()) {
      System.out.println(rental.getResource().getName() + " | " + rental.getLeaseTermInDays() + " dni | "
          + rental.getStatus() + " | " + rental.calculateCost() + " zł");
    }

    System.out.println("Liczba wypożyczeń: " + rentalSystem.getRentals().size());
    for (RentalStatus status : RentalStatus.values()) {
      System.out.println(status + ": " + rentalSystem.countRentalsByStatus(status));
    }
    System.out.println("Łączny koszt: " + rentalSystem.calculateTotalCost() + " zł");
  }
}