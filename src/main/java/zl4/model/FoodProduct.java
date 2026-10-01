package zl4.model;

import java.time.LocalDate;

public class FoodProduct extends Product {

  private final LocalDate expiryDate;


  public FoodProduct(String name, String category, double price, LocalDate expiryDate) {
    super(name, category, price);
    this.expiryDate = expiryDate;
  }

  @Override
  public String getDescription() {
    return super.getDescription() + " | Data ważności: " + expiryDate;
  }
}
