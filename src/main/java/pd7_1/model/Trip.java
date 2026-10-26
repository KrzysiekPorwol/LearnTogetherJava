package pd7_1.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Trip {

  private final Destination destination;
  private final TransportType transportType;
  private final double price;
  private final int durationInMinutes;

  public static Trip of(Destination destination, TransportType transportType, double price, int durationInMinutes) {
    return new Trip(destination, transportType, price, durationInMinutes);
  }
}