package pd1;

import pd1.model.PlayerRegistry;
import pd1.userinput.UserInfoReader;

import java.util.Scanner;

public class LeaderBoard {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    PlayerRegistry playerRegistry = new PlayerRegistry();

    int howMuchPlayers = UserInfoReader.readPlayersCount(scanner);

    UserInfoReader.readPlayers(playerRegistry, howMuchPlayers, scanner);

    playerRegistry.printLeaderBoard();

    playerRegistry.printBestOneRoundScore();
  }
}

