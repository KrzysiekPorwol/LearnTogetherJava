package pd1.model;

import lombok.Getter;

public class Player implements Comparable<Player> {
  @Getter
  private final String name;
  private final double[] scores;
  private final double sumscore;
  private final double avarageScore;
  @Getter
  private final double maxScore;
  private final double minScore;

  public Player(String name, double[] scores) {
    this.name = name;
    this.scores = scores;
    sumscore = scores[0] + scores[1] + scores[2];
    avarageScore = (sumscore / 3.0);
    maxScore = calculateMaxScore();
    minScore = calculateMinScore();
  }

  public double calculateMaxScore() {
    double max = scores[0];

    if (scores[1] > max) {
      max = scores[1];
    }
    if (scores[2] > max) {
      max = scores[2];
    }
    return max;
  }

  public double calculateMinScore() {
    double max = scores[0];

    if (scores[1] < max) {
      max = scores[1];
    }
    if (scores[2] < max) {
      max = scores[2];
    }
    return max;
  }

  @Override
  public String toString() {
    return "Imię: " + name + " | " +
          "Suma punktów: " + sumscore + " | " +
          "Średnia ilość punktów: " + avarageScore + " | " +
          "Najgorszy wynik: " + minScore + " | " +
          "Najlepszy wynik: " + maxScore;
  }

  @Override
  public int compareTo(Player o) {
    return Double.compare(o.sumscore, sumscore);
  }
}