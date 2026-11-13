package pd12.printer;

import pd12.MenuOption;
import pd12.model.OrderResult;
import pd12.model.ProcessingReport;

public class ConsolePrinter {

  public static void printMenu() {
    System.out.println("--- Laptop store: multithreaded order processing ---");
    for (MenuOption option : MenuOption.values()) {
      System.out.println(option.getNumber() + " - " + option.getDescription());
    }
  }

  public static void printReport(ProcessingReport report) {
    if (report.isEmpty()) {
      System.out.println("No orders to process");
      return;
    }
    report.results().forEach(ConsolePrinter::printResult);
    System.out.println("Sold in this run: " + report.soldQuantity());
    System.out.println("Remaining in store: " + report.remainingQuantity());
  }

  private static void printResult(OrderResult result) {
    String status = result.accepted() ? "ACCEPTED" : "REJECTED";
    System.out.println(result.order() + " -> " + status);
  }
}
