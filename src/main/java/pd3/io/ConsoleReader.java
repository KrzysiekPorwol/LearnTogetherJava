package pd3.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader {

  public static int readUserNumber(Scanner scanner) {
    int userNumber;
    while (true) {
      try {
        userNumber = scanner.nextInt();
        scanner.nextLine();
        return userNumber;
      } catch (InputMismatchException e) {
        System.out.println("\nWprowadziłeś niepoprawne dane!\n");
        scanner.nextLine();
        ConsolePrinter.printMenu();
      }
    }
  }
}