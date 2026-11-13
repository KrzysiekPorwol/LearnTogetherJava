package pd12.model;

import org.apache.commons.lang3.Validate;

public record Order(String customerName, int quantity) {

  public Order {
    Validate.notBlank(customerName, "Customer name cannot be empty");
    Validate.isTrue(quantity >= 1, "Quantity must be at least 1");
    customerName = customerName.trim();
  }

  @Override
  public String toString() {
    return "Customer: %s | Laptops: %d".formatted(customerName, quantity);
  }
}