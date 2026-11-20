package pd12.model;

public class Store {

  private static final int INITIAL_QUANTITY = 10;

  private int availableQuantity = INITIAL_QUANTITY;

  public synchronized boolean tryBuy(int quantity) {
    if (quantity > availableQuantity) {
      return false;
    }
    availableQuantity -= quantity;
    return true;
  }

  public synchronized int getAvailableQuantity() {
    return availableQuantity;
  }
}
