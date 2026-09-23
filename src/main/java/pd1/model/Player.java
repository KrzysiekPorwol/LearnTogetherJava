package pd1.model;

public class Player implements Comparable<Player> {
  private final String name;
  private final double score1;
  private final double score2;
  private final double score3;
  private final double sumscore;
  private final double avarageScore;
  private final double maxScore;
  private final double minScore;

  public Player(String name, double score2, double score1, double score3) {
    this.name = name;
    this.score2 = score2;
    this.score1 = score1;
    this.score3 = score3;
    sumscore = score1 + score2 + score3;
    avarageScore = (sumscore / 3);
    maxScore = calculateMaxScore();
    minScore = calculateMinScore();
  }

  public String getName() {
    return name;
  }

  public double getScore1() {
    return score1;
  }

  public double getScore2() {
    return score2;
  }

  public double getScore3() {
    return score3;
  }

  public double getSumscore() {
    return sumscore;
  }

  public double getAvarageScore() {
    return avarageScore;
  }

  public double getMaxScore() {
    return maxScore;
  }

  public double getMinScore() {
    return minScore;
  }

  public double calculateMaxScore() {
    double max = score1;

    if (score2 > max) {
      max = score2;
    }
    if (score3 > max) {
      max = score3;
    }
    return max;
  }

  public double calculateMinScore() {
    double max = score1;

    if (score2 < max) {
      max = score2;
    }
    if (score3 < max) {
      max = score3;
    }
    return max;
  }

  @Override
  public String toString() {
    return "Imię: " + name + " | " +
          "Suma punktów: " + sumscore + " | " +
          "Średnia ilość punktów: " + avarageScore +
          " | " + "Najgorszy wynik: " +
          minScore + " | " +
          "Najlepszy wynik: " + maxScore;
  }

  //// Wyświetli leaderboard: miejsce, imię, suma, średnia, min, max

  @Override
  public int compareTo(Player o) {
    return Double.compare(o.sumscore, sumscore);
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