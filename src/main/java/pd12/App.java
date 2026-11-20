package pd12;

import pd12.model.Store;
import pd12.repository.OrderRepository;
import pd12.service.OrderProcessor;
import pd12.service.OrderService;
import pd12.service.SimulationService;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    OrderProcessor orderProcessor = new OrderProcessor();
    OrderRepository orderRepository = new OrderRepository();
    Store store = new Store();

    OrderService orderService = new OrderService(orderRepository, store, orderProcessor);
    SimulationService simulationService = new SimulationService(orderProcessor);

    MenuController menuController = new MenuController(scanner, orderService, simulationService);

    menuController.runMenu();
  }
}