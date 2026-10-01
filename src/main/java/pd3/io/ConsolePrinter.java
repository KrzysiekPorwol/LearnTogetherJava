package pd3.io;

import pd3.model.MenuOption;

public class ConsolePrinter {

  public static void printMenu() {
    System.out.println("Wpisz odpowiednią cyfrę: \n");
    for (MenuOption m : MenuOption.values()) {
      System.out.println(m.getNumber() + " - " + m.getDescription());
    }
  }

  public static void printNumberResult(long result) {
    System.out.println("\nWynik to: " + result + "\n\n");
  }

  public static void printCompareResult(double result) {
    if (result > 0) {
      System.out.println("\nIteracja była średnio szybsza o " + result + " ns\n\n");
    } else {
      System.out.println("\nRekurencja była średnio szybsza o " + (-result) + " ns\n\n");
    }
  }

  public static void printNumberResult(double result) {
    System.out.println("\nWynik to: " + result + "\n\n");
  }

  public static void printNumberInfo(boolean result) {
    if (result) {
      System.out.println("\n Podana liczba to liczba pierwsza \n\n");
    } else {
      System.out.println("\n Podana liczba nie jest liczbą pierwszą \n\n");

    }
  }
}
