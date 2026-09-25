package pd1.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerRegistry {

  private final List<Player> players = new ArrayList<>();

  public void addPlayer(Player player) {
    players.add(player);
  }

  public void printLeaderBoard() {

    int count = 1;
    for (int i = 0; i < players.size(); i++) {

      System.out.println("Miejsce: " + count);
      System.out.println(players.get(i));
      count++;
    }
  }

  public void printBestOneRoundScore() {
    if (players.isEmpty()) {
      System.out.println("Lista graczy jest pusta.");
      return;
    }
    double bestTime = players.get(0).getMaxScore();
    Player player = players.get(0);
    for (Player p : players) {
      if (p.getMaxScore() > bestTime) {
        bestTime = p.getMaxScore();
        player = p;
      }
    }
    System.out.println("Wyróżniony gwiazdką został " + player.getName() +
          " zdobył najwiekszą ilość punktów w jednej rundzie, aż " + bestTime);
  }
}
