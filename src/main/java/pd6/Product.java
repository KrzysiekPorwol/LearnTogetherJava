package pd6;

public class Product implements Entity<String> {

  private final String id;
  private final String productName;

  public Product(String id, String productName) {
    this.id = id;
    this.productName = productName;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Product{id='" + id + "', name='" + productName + "'}";
  }
}