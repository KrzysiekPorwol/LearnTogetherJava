package zl4;

import zl4.model.Electronics;
import zl4.model.FoodProduct;
import zl4.model.Product;
import zl4.model.Warehouse;

import java.time.LocalDate;

public class Shop {

  public static void main(String[] args) {
    Warehouse warehouse = new Warehouse();

    warehouse.addProduct(new Electronics("Telewizor", "RTV", 2499.99, 24));
    warehouse.addProduct(new Electronics("Laptop", "Komputery", 3899.00, 36));
    warehouse.addProduct(new Electronics("Słuchawki", "Audio", 299.90, 12));

    warehouse.addProduct(new FoodProduct("Mleko", "Nabiał", 3.49, LocalDate.of(2026, 10, 5)));
    warehouse.addProduct(new FoodProduct("Chleb", "Pieczywo", 5.99, LocalDate.of(2026, 9, 30)));

    for (String description : warehouse.getProductDescriptions()) {
      System.out.println(description);
    }
  }
}

