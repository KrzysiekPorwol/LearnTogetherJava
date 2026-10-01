package zl4.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

  private final List<Product> products = new ArrayList<>();

  public void addProduct(Product product) {
    products.add(product);
  }

  public List<String> getProductDescriptions() {
    List<String> descriptions = new ArrayList<>();
    for (Product p : products) {
      descriptions.add(p.getDescription());
    }
    return descriptions;
  }
}
