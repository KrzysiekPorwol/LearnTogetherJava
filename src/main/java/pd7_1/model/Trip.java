package pd7_1.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class Trip {

  private final Destination destination;
  private final TransportType transportType;
  private final double price;
  private final int durationInMinutes;

}
