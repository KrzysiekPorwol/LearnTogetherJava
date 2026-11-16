package pd13;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateCalculator {

  private static final Set<Locale> AVAILABLE_LOCALES = Set.copyOf(Arrays.asList(Locale.getAvailableLocales()));

  private final Clock clock;
  private final Set<LocalDate> holidays;


  public DateCalculator(Clock clock, Set<LocalDate> holidays) {
    this.holidays = Objects.requireNonNull(holidays, "holidays must not be null");
    this.clock = Objects.requireNonNull(clock, "clock must not be null");
  }

  public int getAge(LocalDate birthDate) {
    Objects.requireNonNull(birthDate, "birthDate must not be null");
    LocalDate today = LocalDate.now(clock);
    if (birthDate.isAfter(today)) {
      throw new IllegalArgumentException(
          "birthDate " + birthDate + " is in the future (today is " + today + ")");
    }
    return Period.between(birthDate, today).getYears();
  }

  public LocalDate nextPayday(LocalDate from) {
    Objects.requireNonNull(from, "from must not be null");
    LocalDate payday = lastBusinessDayOfMonth(from);

    if (payday.isBefore(from)) {
      payday = lastBusinessDayOfMonth(from.plusMonths(1));
    }

    return payday;
  }

  private LocalDate lastBusinessDayOfMonth(LocalDate date) {
    LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
    DayOfWeek dayOfWeek = lastDay.getDayOfWeek();

    if (dayOfWeek == DayOfWeek.SATURDAY) {
      return lastDay.minusDays(1);
    }
    if (dayOfWeek == DayOfWeek.SUNDAY) {
      return lastDay.minusDays(2);
    }
    return lastDay;
  }

  /**
   * <p>Zakładam, że dzień {@code from} wliczamy, a dnia {@code to} nie. Dlatego używam
   * {@code datesUntil} - wlicza datę, na której jest wywołany, a nie wlicza tej z argumentu.
   * Dodatkowo, jeśli policzymy okres od a do b, a potem od b do c, to razem wyjdzie okres od a
   * do c. Gdyby dzień {@code to} był wliczany, to przy łączeniu okresów dzień b liczyłby się
   * dwa razy.
   */
  public long businessDaysBetween(LocalDate from, LocalDate to) {
    Objects.requireNonNull(from, "from must not be null");
    Objects.requireNonNull(to, "to must not be null");
    if (to.isBefore(from)) {
      throw new IllegalArgumentException("to (" + to + ") must not be before from (" + from + ")");
    }

    return from.datesUntil(to)
        .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY)
        .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
        .filter(date -> !holidays.contains(date))
        .count();
  }

  /**
   * Uważać na metodę withZoneSameLocal!!
   */
  public ZonedDateTime convertTimezone(ZonedDateTime sourceDateTime, String targetZone) {
    Objects.requireNonNull(sourceDateTime, "sourceDateTime must not be null");
    Objects.requireNonNull(targetZone, "targetZone must not be null");
    ZoneId zone = ZoneId.of(targetZone);
    return sourceDateTime.withZoneSameInstant(zone);
  }

  public String formatForLocale(LocalDateTime dateTime, String languageTag) {
    Locale locale = parseLocale(languageTag);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
    return dateTime.format(formatter);
  }

  private Locale parseLocale(String languageTag) {
    if (languageTag == null || languageTag.isBlank()) {
      throw new IllegalArgumentException("Language tag must not be null or blank");
    }

    Locale locale;
    try {
      locale = new Locale.Builder().setLanguageTag(languageTag).build();
    } catch (IllformedLocaleException e) {
      throw new IllegalArgumentException("Invalid language tag format: " + languageTag, e);
    }

    if (!AVAILABLE_LOCALES.contains(locale)) {
      throw new IllegalArgumentException("Unsupported language tag: " + languageTag);
    }

    return locale;
  }

  /**
   * STRICT - zabraniamy javie naprawiać samemu błędne daty
   * zamieniamy y na u poniewaz strick dziala na zasadzie ze
   * uwzględnia ere, a domyślny tryb (SMART) zakłada z góry
   * że chodzi o naszą erę.
   */
  public boolean isValidDate(String input, String pattern) {
    if (input == null || pattern == null) {
      return false;
    }

    try {
      DateTimeFormatter formatter = DateTimeFormatter
          .ofPattern(pattern.replace('y', 'u'))
          .withResolverStyle(ResolverStyle.STRICT);

      LocalDate.parse(input, formatter);
      return true;
    } catch (DateTimeParseException | IllegalArgumentException e) {
      return false;
    }
  }
}