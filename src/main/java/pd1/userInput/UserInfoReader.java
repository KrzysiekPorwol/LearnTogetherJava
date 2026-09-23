package pd1.userInput;

import pd1.model.Player;
import pd1.model.PlayerList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInfoReader {

  public static int readUserHowMuchPlayers(Scanner scanner) {

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
        System.out.println("Wprowadziłeś błędne dane, spróbuj ponownie");
      }

    } while (!playerLoadingSuccessful);
    return howMuchPlayers;
  }

  public static void readUserInfoPlayers(PlayerList playerList, int howMuchPlayers, Scanner scanner) {

    double[] scores = new double[3];
    for (int i = 0; i < howMuchPlayers; i++) {
      System.out.println("Imię " + (i + 1) + " gracza: ");
      String name = scanner.nextLine();
      for (int j = 0; j < 3; j++) {
        System.out.println("Podaj ilość punktów w " + (j + 1) + " rundzie: ");
        scores[j] = scanner.nextDouble();
        scanner.nextLine();
      }
      Player player = new Player(name, scores[0], scores[1], scores[2]);
      playerList.addPlayer(player);
    }
  }
}

//PD-1
//Leaderboard turniejowy
//
//Napisz program który:
//Wczyta od użytkownika liczbę graczy N (2-10)
//Dla każdego gracza wczyta imię i 3 wyniki (pętle zagnieżdżone)
//      Obliczy sumę, średnią, min i max dla każdego gracza
//Posortuje graczy malejąco po sumie punktów (własna implementacja sortowania)
//Wyświetli leaderboard: miejsce, imię, suma, średnia, min, max
//Wyróżni gwiazdką gracza z najwyższym POJEDYNCZYM wynikiem

//rzeczownik: gracz (liczba graczy 2-10)
