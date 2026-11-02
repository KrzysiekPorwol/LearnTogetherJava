package pd9.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Event {

  private final String nameEvent;
  private final String city;
  private final int participants;
  private final LocalDate date;
  private final boolean free;

  public static Event of(String nameEvent, String city, int participants, LocalDate date, boolean free) {
    return new Event(nameEvent, city, participants, date, free);
  }
}
