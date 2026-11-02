package pd9;

import pd9.model.Event;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventProcessor {

  public void process(List<Event> events, List<Predicate<Event>> filters,
          Function<Event, String> formatter, Consumer<String> output) {
    Predicate<Event> allFilters = combine(filters);
    events.forEach(event -> {
      if (allFilters.test(event)) {
        output.accept(formatter.apply(event));
      }
    });
  }

  private Predicate<Event> combine(List<Predicate<Event>> filters) {
    Predicate<Event> combined = event -> true;
    for (Predicate<Event> filter : filters) {
      combined = combined.and(filter);
    }
    return combined;
  }
}