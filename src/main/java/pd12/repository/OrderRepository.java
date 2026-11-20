package pd12.repository;

import pd12.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

  private final List<Order> orders;

  public OrderRepository() {
    this.orders = new ArrayList<>();
  }

  public void add(Order order) {
    orders.add(order);
  }

  public List<Order> findAll() {
    return List.copyOf(orders);
  }

  public List<Order> takeAll() {
    List<Order> takenOrders = new ArrayList<>(orders);
    orders.clear();
    return takenOrders;
  }
}
