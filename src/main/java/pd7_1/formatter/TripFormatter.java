package pd7_1.formatter;

import lombok.experimental.UtilityClass;
import pd7_1.model.Trip;

@UtilityClass
public class TripFormatter {

  public static String writeTripDescription(Trip trip) {
    return
        "Country: " + trip.getDestination().getCountry()
            + " | " + "Destination: " + trip.getDestination().getName()
            + " | " + "Price: " + trip.getPrice()
            + " | " + "Transport type: " + trip.getTransportType().getDescription()
            + " | " + "Duration in minutes: " + trip.getDurationInMinutes();
  }
}