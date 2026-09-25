package pd1.userinput;

import pd1.model.Player;
import pd1.model.PlayerRegistry;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInfoReader {

  public static int readPlayersCount(Scanner scanner) {

    boolean playerLoadingSuccessful = false;
    int howMuchPlayers = 0;

    do {
      try {
        System.out.println("Podaj liczbę graczy: (2 - 10)");

        howMuchPlayers = scanner.nextInt();
        scanner.nextLine();
        if (howMuchPlayers < 2 || howMuchPlayers > 10) {
          System.out.println("liczba jest spoza zakresu! spróbuj ponownie");
          continue;
        }

        playerLoadingSuccessful = true;

      } catch (InputMismatchException e) {
        scanner.nextLine();
        // scanner.nextLine() w catch wyrzuca błędną linię z bufora,
        // (spowodowanym scanner.nextInt() w linii 20)
        // więc w następnym obrocie pętli Scanner czeka na nowy input. (inaczej dojdzie do pętli nieskończonej)
        System.out.println("Wprowadziłeś błędne dane, spróbuj ponownie");
      }

    } while (!playerLoadingSuccessful);
    return howMuchPlayers;
  }

  public static PlayerRegistry readPlayers(PlayerRegistry playerRegistry, int howMuchPlayers, Scanner scanner) {

    double[] scores = new double[3];
    Player player = null;
    for (int i = 0; i < howMuchPlayers; i++) {
      System.out.println("Imię " + (i + 1) + " gracza: ");
      String name = scanner.nextLine();
      for (int j = 0; j < 3; j++) {
        boolean validInput = false;
        while (!validInput) {
          System.out.println("Podaj ilość punktów w " + (j + 1) + " rundzie: ");
          try {
            scores[j] = scanner.nextDouble();
            validInput = true;
          } catch (InputMismatchException e) {
            System.out.println("Wprowadziłeś niepoprawne dane, spróbuj jeszcze raz!");
          } finally {
            scanner.nextLine();
          }
        }
      }
      player = new Player(name, scores);
      playerRegistry.addPlayer(player);
    }
    return playerRegistry;
  }
}
