package zl4.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Product {
  private final String name;
  private final String category;
  private final double price;

  protected String getDescription() {
    return "Nazwa: " + name + " | Kategoria: " + category + " | Cena za sztukę: " + price;
  }
}
