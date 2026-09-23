package pd1;

import pd1.model.PlayerList;
import pd1.userInput.UserInfoReader;

import java.util.Scanner;

public class LeaderBoard {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    PlayerList playerList = new PlayerList();

    int howMuchPlayers = UserInfoReader.readUserHowMuchPlayers(scanner);

    UserInfoReader.readUserInfoPlayers(playerList, howMuchPlayers, scanner);

    playerList.showLeaderBoard();

    playerList.showBestOneRoundScore();
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