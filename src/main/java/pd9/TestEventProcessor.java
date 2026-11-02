package pd9;

import pd9.model.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class TestEventProcessor {

  private static final LocalDate TODAY = LocalDate.of(2026, 10, 29);

  public static void main(String[] args) {
    List<Event> events = List.of(
            Event.of("Taco Hemingway", "Kraków", 120, LocalDate.of(2026, 11, 15), true),
            Event.of("Pezet", "Warszawa", 30, LocalDate.of(2026, 12, 3), false),
            Event.of("PROBLEM", "Kraków", 45, LocalDate.of(2026, 9, 20), true),
            Event.of("Quebonafide", "Wrocław", 400, LocalDate.of(2027, 2, 10), false),
            Event.of("Białas", "Kraków", 15, LocalDate.of(2026, 11, 2), true),
            Event.of("Mata", "Warszawa", 800, LocalDate.of(2026, 10, 1), false)
    );

    Predicate<Event> isFree = Event::isFree;
    Predicate<Event> inKrakow = event -> event.getCity().equals("Kraków");
    Predicate<Event> isUpcoming = event -> event.getDate().isAfter(TODAY);
    Predicate<Event> isBig = event -> event.getParticipants() >= 100;

    Function<Event, String> lowerCase = event -> (event.getNameEvent()
            + " | " + event.getCity()
            + " | " + event.getParticipants() + " osób"
            + " | " + event.getDate());
    Function<Event, String> upperCase = event -> (event.getNameEvent()
            + " | " + event.getCity()
            + " | " + event.getParticipants() + " osób"
            + " | " + event.getDate());

    Consumer<String> console = System.out::println;
    Consumer<String> bulletList = text -> System.out.println("  - " + text);

    EventProcessor processor = new EventProcessor();

    System.out.println("1. Darmowe i w Krakowie (małe litery)");
    processor.process(events, List.of(isFree, inKrakow, isUpcoming), lowerCase, console);

    System.out.println("\n2. Duże wydarzenia (wielkie litery, lista)");
    processor.process(events, List.of(isBig), upperCase, bulletList);

    System.out.println("\n3. Płatne poza Krakowem — negate()");
    processor.process(events, List.of(isFree.negate(), inKrakow.negate()), lowerCase, console);

    System.out.println("\nBrak filtrów — wszystkie wydarzenia");
    processor.process(events, List.of(), upperCase, bulletList);

    System.out.println("\n5. Zbieranie wyników do listy zamiast wypisywania");
    List<String> upcomingNames = new ArrayList<>();
    processor.process(events, List.of(isUpcoming), lowerCase, upcomingNames::add);
    System.out.println("Nadchodzące: " + upcomingNames);

  }
}
