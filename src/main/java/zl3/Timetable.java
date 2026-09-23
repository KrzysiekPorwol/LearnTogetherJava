package zl3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Timetable {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    boolean userDayOfTheWeekIsIncorrect = true;

    do {
      try {
        System.out.println("Podaj który dzień tygodnia chcesz wyświetlić: (1 - 7)");
        int day = scanner.nextInt();

        userDayOfTheWeekIsIncorrect = Day.isDayOutOfRange(day);

        Day.getByDayOFTheWeek(day).ifPresent(
              chosenDay -> {
                switch (chosenDay) {
                  case MONDAY, THUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                    printDayInfo(chosenDay);
                    System.out.println("Godzina odjazdu: 5:30");
                  }
                  case SATURDAY -> {
                    printDayInfo(chosenDay);
                    System.out.println("Godzina odjazdu: 7:00");
                  }
                  case SUNDAY -> {
                    printDayInfo(chosenDay);
                    System.out.println("Godzina odjazdu: 9:00");
                  }
                }
              }
        );

      } catch (InputMismatchException e) {
        scanner.nextLine();
        System.out.println("Nie wprowadziłeś liczby całkowitej.");
      }

    } while (userDayOfTheWeekIsIncorrect);

    scanner.close();

  }

  public static void printDayInfo(Day day) {
    System.out.println(day.getDescription());
    System.out.println(printWeekend(day));
  }

  public static String printWeekend(Day day) {
    return day.isWeekend() ? "Wybrany dzień to dzien weekendowy" : "Wybrany dzień to dzień roboczy";
  }
}

//ZL-3
//ZADANIE NA ZAJĘCIACH (15 min): Automatyczny rozkład jazdy
//
//Napisz program który dla podanego numeru dnia tygodnia (1-7) wyświetla:
//Nazwę dnia (użyj switch expression Java 17)
//Czy to dzień roboczy czy weekend
//Godzinę odjazdu pierwszego autobusu:
//Pn-Pt: 05:30  |  So: 07:00  |  Nd: 09:00
//Bonus: dla liczby spoza zakresu 1-7 wyświetl komunikat błędu