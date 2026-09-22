package zl3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Timetable {

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    boolean userDayOfTheWeekIsIncorrect = true;

    do {
      try {
        System.out.println("Podaj który dzień tygodnia chcesz wyświetlić: (1 - 7)");
        int day = input.nextInt();

        // Początkowo tutaj chciałem dać input.nextline żeby wyczyścić bufor, ale skoro tylko raz
        // pytamy o dane od użytkownika, to bezpieczniej wydaje mi się tutaj od razu
        // zamknąć scannera.
        input.close();

        if (day > Day.SUNDAY.getDayOfTheWeek() || day < Day.MONDAY.getDayOfTheWeek()) {
          System.out.println("Liczba spoza zakresu");
          continue;
        }


        // Tutaj niestety zrobiłem antypattern, czyli zrobilem isPresent a w nim get (Optional).
        if (Day.getByDayOFTheWeek(day).isPresent()) {

          Day chosenDay = Day.getByDayOFTheWeek(day).get();

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
          userDayOfTheWeekIsIncorrect = false;
        }

      } catch (InputMismatchException e) {
        input.nextLine();
        System.out.println("Nie wprowadziłeś liczby całkowitej.");
      }

    } while (userDayOfTheWeekIsIncorrect);
  }

  public static void printDayInfo(Day day) {
    System.out.println(day.getDescription());
    System.out.println(printWeekend(day.isWeekend()));
  }

  public static String printWeekend(boolean isWeekend) {
    return isWeekend ? "Wybrany dzień to dzień weekendowy" : "Wybrany dzień to dzień roboczy";
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