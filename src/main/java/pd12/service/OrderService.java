package pd12.service;

import lombok.RequiredArgsConstructor;
import pd12.model.Order;
import pd12.model.ProcessingReport;
import pd12.model.Store;
import pd12.repository.OrderRepository;

import java.util.List;

@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final Store store;
  private final OrderProcessor orderProcessor;

  public void addOrder(Order order) {
    orderRepository.add(order);
  }

  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  public ProcessingReport processPendingOrders() {
    return orderProcessor.process(orderRepository.takeAll(), store);
  }
}
