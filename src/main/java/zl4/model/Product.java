package zl4.model;

public abstract class Product {
  private final String name;
  private final String category;
  private final double price;

  public Product(String name, String category, double price) {
    this.name = name;
    this.category = category;
    this.price = price;
  }

  protected String getDescription() {
    return "Nazwa: " + name + " | Kategoria: " + category + " | Cena za sztukę: " + price;
  }
}
