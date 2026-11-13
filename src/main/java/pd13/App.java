package pd13;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.function.Supplier;

public class App {

  private static final ZoneId WARSAW = ZoneId.of("Europe/Warsaw");

  public static void main(String[] args) {

    Clock clock = Clock.fixed(LocalDate.of(2026, 11, 13).atStartOfDay(WARSAW).toInstant(), WARSAW);
    Set<LocalDate> holidays = Set.of(LocalDate.of(2026, 11, 11));
    DateCalculator calc = new DateCalculator(clock, holidays);

    System.out.println("--- getAge ---");
    run("urodzony 20.12.2000 (urodziny jeszcze przed nim)", () -> calc.getAge(LocalDate.of(2000, 12, 20)));
    run("data urodzenia w przyszłości", () -> calc.getAge(LocalDate.of(2030, 1, 1)));

    System.out.println("--- nextPayday ---");
    run("od 31.10.2026", () -> calc.nextPayday(LocalDate.of(2026, 10, 31)));
    run("null zamiast daty", () -> calc.nextPayday(null));

    System.out.println("--- businessDaysBetween ---");
    run("09.11 -> 16.11", () -> calc.businessDaysBetween(LocalDate.of(2026, 11, 9), LocalDate.of(2026, 11, 16)));
    run("daty zamienione miejscami", () -> calc.businessDaysBetween(LocalDate.of(2026, 11, 16), LocalDate.of(2026, 11, 9)));

    System.out.println("--- convertTimezone ---");
    ZonedDateTime noonInWarsaw = ZonedDateTime.of(2026, 11, 13, 12, 0, 0, 0, WARSAW);
    run("Warszawa -> Nowy Jork", () -> calc.convertTimezone(noonInWarsaw, "America/New_York"));
    run("nieistniejąca strefa", () -> calc.convertTimezone(noonInWarsaw, "Mars/Olympus"));

    System.out.println("--- formatForLocale ---");
    LocalDateTime dateTime = LocalDateTime.of(2026, 11, 13, 10, 0);
    run("po polsku", () -> calc.formatForLocale(dateTime, "pl-PL"));
    run("nieobsługiwany język", () -> calc.formatForLocale(dateTime, "xx-YY"));

    System.out.println("--- isValidDate ---");
    run("28.02.2026", () -> calc.isValidDate("2026-02-28", "yyyy-MM-dd"));
    run("30.02.2026", () -> calc.isValidDate("2026-02-30", "yyyy-MM-dd"));
  }

  // z racji że skupiamy sie na time, pozwoliłem na łapanie każdego wyjątku
  private static void run(String description, Supplier<Object> action) {
    try {
      System.out.println("  " + description + " -> " + action.get());
    } catch (Exception e) {
      System.out.println("  " + description + " -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }
  }
}

//PD-13: Kalkulator dat
//
//Stwórz DateCalculator z metodami:
//
//age(LocalDate birthDate) → int (wiek w latach)
//nextPayday(LocalDate from) → LocalDate (ostatni dzień roboczy miesiąca)
//businessDaysBetween(LocalDate from, LocalDate to) → long (dni roboczych)
//convertTimezone(ZonedDateTime dt, String targetZone) → ZonedDateTime
//formatForLocale(LocalDateTime dt, String languageTag) → String (dd MMMM yyyy)
//isValidDate(String input, String pattern) → boolean (safe parse)
