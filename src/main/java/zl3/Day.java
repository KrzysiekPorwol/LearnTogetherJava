package zl3;

import java.util.Optional;

public enum Day {
  MONDAY(1, "Poniedziałek"),
  THUESDAY(2, "Wtorek"),
  WEDNESDAY(3, "Środa"),
  THURSDAY(4, "Czwartek"),
  FRIDAY(5, "Piątek"),
  SATURDAY(6, "Sobota"),
  SUNDAY(7, "Niedziela");

  private final int dayOfTheWeek;
  private final String description;

  Day(int numberRepresentation, String description) {
    this.description = description;
    this.dayOfTheWeek = numberRepresentation;
  }

  public int getDayOfTheWeek() {
    return dayOfTheWeek;
  }

  public String getDescription() {
    return description;
  }

  public static Optional<Day> getByDayOFTheWeek(int number) {
    for (Day d : Day.values()) {
      if (number == d.getDayOfTheWeek()) {
        return Optional.of(d);
      }
    }
    return Optional.empty();
  }



  public boolean isWeekend() {
    if (dayOfTheWeek == SATURDAY.dayOfTheWeek || dayOfTheWeek == SUNDAY.dayOfTheWeek) {
      return true;
    } else {
      return false;
    }
  }


}
