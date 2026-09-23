package pd1.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {

  private final List<Player> players = new ArrayList<>();

  public void addPlayer(Player player) {
    players.add(player);
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void showLeaderBoard() {
    int count = 1;
    for (Player p : players) {
      System.out.println("Miejsce: " + count);
      System.out.println(p);
      count++;
    }
  }

  public void showBestOneRoundScore () {
    double bestTime = players.get(0).getMaxScore();
    Player player = players.get(0);
    for (Player p : players) {
      if (p.getMaxScore() > bestTime) {
        bestTime = p.getMaxScore();
        player = p;
      }
    }
    System.out.println("Wyróżniony gwiazdką został " + player.getName() +
          "zdobył najwiekszą ilość punktów, aż " + bestTime);
  }

  //Wyświetli leaderboard: miejsce, imię, suma, średnia, min, max

}
