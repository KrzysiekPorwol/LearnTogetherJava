package pd12;

import lombok.RequiredArgsConstructor;
import pd12.model.Order;
import pd12.printer.ConsolePrinter;
import pd12.service.OrderService;
import pd12.service.SimulationService;

import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class MenuController {

  private final Scanner scanner;
  private final OrderService orderService;
  private final SimulationService simulationService;

  public void runMenu() {
    MenuOption option;
    do {
      option = readMenuOption();
      handleOption(option);
    } while (option != MenuOption.EXIT);
  }

  private void handleOption(MenuOption option) {
    switch (option) {
      case EXIT -> System.out.println("Goodbye!");
      case ADD_ORDER -> addOrder();
      case SHOW_ORDERS -> showOrders();
      case PROCESS_ORDERS -> ConsolePrinter.printReport(orderService.processPendingOrders());
      case RUN_SIMULATION -> ConsolePrinter.printReport(simulationService.runSimulation());
    }
  }

  private void addOrder() {
    String customerName = readCustomerName();
    int quantity = readQuantity();

    orderService.addOrder(new Order(customerName, quantity));
    System.out.println("Order added: " + customerName + ", laptops: " + quantity);
  }

  private void showOrders() {
    System.out.println(orderService.findAll());
  }

  private MenuOption readMenuOption() {
    while (true) {
      ConsolePrinter.printMenu();
      try {
        int number = Integer.parseInt(scanner.nextLine().trim());
        Optional<MenuOption> option = MenuOption.fromNumber(number);
        if (option.isPresent()) {
          return option.get();
        }
        System.err.println("No option with this number. Try again.");
      } catch (NumberFormatException e) {
        System.err.println("Please enter a number. Try again.");
      }
    }
  }

  private String readCustomerName() {
    while (true) {
      System.out.println("Enter your name: ");
      String customerName = scanner.nextLine().trim();
      if (!customerName.isEmpty()) {
        return customerName;
      }
      System.err.println("Name cannot be empty. Try again.");
    }
  }

  private int readQuantity() {
    while (true) {
      System.out.println("Enter the number of laptops: ");
      try {
        int quantity = Integer.parseInt(scanner.nextLine().trim());
        if (quantity > 0) {
          return quantity;
        }
        System.err.println("Quantity must be greater than 0. Try again.");
      } catch (NumberFormatException e) {
        System.err.println("Quantity must be a whole number. Try again.");
      }
    }
  }
}
