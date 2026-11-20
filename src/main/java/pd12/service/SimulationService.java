package pd12.service;

import lombok.RequiredArgsConstructor;
import pd12.model.Order;
import pd12.model.ProcessingReport;
import pd12.model.Store;

import java.util.List;

@RequiredArgsConstructor
public class SimulationService {

  private static final List<Order> SAMPLE_ORDERS = List.of(
      new Order("Anna", 8),
      new Order("Bartek", 5),
      new Order("Celina", 3),
      new Order("Darek", 2),
      new Order("Ewa", 4),
      new Order("Filip", 1)
  );

  private final OrderProcessor orderProcessor;

  public ProcessingReport runSimulation() {
    return orderProcessor.process(SAMPLE_ORDERS, new Store());
  }
}
