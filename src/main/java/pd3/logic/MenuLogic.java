package pd3.logic;

import pd3.io.ConsolePrinter;
import pd3.io.ConsoleReader;
import pd3.model.Menu;

import java.util.Optional;
import java.util.Scanner;

public class MenuLogic {

  private final Scanner scanner;

  public MenuLogic(Scanner scanner) {
    this.scanner = scanner;
  }

  public void navigateMenu() {


    do {
      ConsolePrinter.printMenu();
      int number = ConsoleReader.readUserNumber(scanner);
      Optional<Menu> menu = Menu.findByNumber(number);
      menu.ifPresentOrElse(
            chosenMenu -> {
              switch (chosenMenu) {
                case FACTORIAL_ITERATOR -> runFactorialIterator();
                case FACTORIAL_RECURSION -> runFactorialRecursion();
                case ISPRIME -> runIsPrime();
                case SIEVEOFERATOSTHENES -> System.out.println("Pokonała mnie ta abstrakcja :(");
                case GCD -> runGCD();
                case POWER -> runPower();
                case BENCHMARK -> runCompareSpeedFactorial();
                case QUIT_PROGRAM -> {
                  System.out.println("\nProgram zostaje zamknięty");
                  System.exit(0);
                }
              }
            },
            () -> {
              System.out.println("\nWpisałeś niepoprawną liczbe!\n");
            });
    } while (true);
  }

  private void runCompareSpeedFactorial() {
    double compareResult = MathLibrary.compareSpeedFactorial();
    ConsolePrinter.printCompareResult(compareResult);
  }


  private void runPower() {
    System.out.println("Wprowadź liczbę którą chcesz potęgować: ");
    int firstNumberPower = ConsoleReader.readUserNumber(scanner);
    System.out.println("Do jakiej potęgi: ");
    int secondNumberPower = ConsoleReader.readUserNumber(scanner);
    double powerResult = MathLibrary.power(firstNumberPower, secondNumberPower);
    ConsolePrinter.printNumberResult(powerResult);
  }

  private void runGCD() {
    System.out.println("Wprowadź pierwszą z dwóch liczb, z których chcesz uzyskać NWD: ");
    int firstNumberGCD = ConsoleReader.readUserNumber(scanner);
    System.out.println("Wprowadź drugą z dwóch liczb, z których chcesz uzyskać NWD: ");
    int secondNumberGCD = ConsoleReader.readUserNumber(scanner);
    int gcdNumber = MathLibrary.gcd(firstNumberGCD, secondNumberGCD);
    ConsolePrinter.printNumberResult(gcdNumber);
  }

  private void runIsPrime() {
    System.out.println("Wprowadź liczbę która chcesz sprawdzić czy jest liczbą pierwszą: ");
    int isPrimeNumber = ConsoleReader.readUserNumber(scanner);
    boolean PrimeNumber = MathLibrary.isPrime(isPrimeNumber);
    ConsolePrinter.printBooleanResult(PrimeNumber);
  }

  private void runFactorialIterator() {
    System.out.println("Wprowadź liczbę aby policzyć silnie: ");
    int factorialNumber = ConsoleReader.readUserNumber(scanner);
    long factorialResult = MathLibrary.factorialIterator(factorialNumber);
    ConsolePrinter.printNumberResult(factorialResult);
  }

  private void runFactorialRecursion() {
    System.out.println("Wprowadź liczbę aby policzyć silnie: ");
    int factorialNumber = ConsoleReader.readUserNumber(scanner);
    long factorialResult = MathLibrary.factorialRecursion(factorialNumber);
    ConsolePrinter.printNumberResult(factorialResult);
  }
}
