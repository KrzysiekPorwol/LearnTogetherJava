package pd7_1.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class Trip {

  private final Destination destination;
  private final TransportType transportType;
  private final double price;
  private final int durationInMinutes;

  @Override
  public String toString() {
      return
          "Country: " + getDestination().getCountry()
              + " | " + "Destination: " + getDestination().getName()
              + " | " + "Price: " + getPrice()
              + " | " + "Transport type: " + getTransportType().getDisplayName()
              + " | " + "Duration in minutes: " + getDurationInMinutes();
    }
}
