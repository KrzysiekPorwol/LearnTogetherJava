package pd6;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Product implements Identifiable<String> {

  @Getter
  private final String id;
  private final String productName;

  @Override
  public String toString() {
    return "Product{id='" + id + "', name='" + productName + "'}";
  }
}