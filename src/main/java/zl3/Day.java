package zl3;

import java.util.Optional;

public enum Day {
  MONDAY(1, "Poniedziałek", false),
  THUESDAY(2, "Wtorek", false),
  WEDNESDAY(3, "Środa", false),
  THURSDAY(4, "Czwartek", false),
  FRIDAY(5, "Piątek", false),
  SATURDAY(6, "Sobota", true),
  SUNDAY(7, "Niedziela", true);

  private final int dayOfTheWeek;
  private final String description;
  private final boolean isWeekend;

  Day(int numberRepresentation, String description, boolean isWeekend) {
    this.description = description;
    this.dayOfTheWeek = numberRepresentation;
    this.isWeekend = isWeekend;
  }

  public int getDayOfTheWeek() {
    return dayOfTheWeek;
  }

  public String getDescription() {
    return description;
  }

  public boolean isWeekend() { return isWeekend; }

  public static Optional<Day> getByDayOFTheWeek(int number) {
    for (Day d : Day.values()) {
      if (number == d.getDayOfTheWeek()) {
        return Optional.of(d);
      }
    }
    return Optional.empty();
  }

  public static boolean isDayOutOfRange(int day) {
    if (day > Day.SUNDAY.getDayOfTheWeek() || day < Day.MONDAY.getDayOfTheWeek()) {
      System.out.println("Liczba spoza zakresu");
      return true;
    }
    return false;
  }
}
