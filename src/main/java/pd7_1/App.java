package pd7_1;

import pd7_1.model.Destination;
import pd7_1.model.TransportType;
import pd7_1.model.Trip;
import pd7_1.model.User;
import pd7_1.repository.TripRepository;
import pd7_1.service.TripService;

public class App {

  private static final String NOT_FOUND_TRIP = "No trip available";

  public static void main(String[] args) {

    TripRepository tripRepository = new TripRepository();
    TripService tripService = new TripService(tripRepository);

    User adam = User.of("Adam", null, null);
    User ewa = User.of("Ewa", TransportType.TRAIN, null);
    User marek = User.of("Marek", null, 200.0);
    User kasia = User.of("Kasia", TransportType.PLANE, 500.0);
    User tomek = User.of("Tomek", TransportType.CAR, 100.0);

    tripRepository.add(Trip.of(Destination.of("Krakow", "Poland"), TransportType.TRAIN, 89.99, 150));
    tripRepository.add(Trip.of(Destination.of("Gdansk", "Poland"), TransportType.PLANE, 349.00, 70));
    tripRepository.add(Trip.of(Destination.of("Vienna", "Austria"), TransportType.CAR, 320.00, 300));
    tripRepository.add(Trip.of(Destination.of("Rome", "Italy"), TransportType.PLANE, 690.00, 140));
    tripRepository.add(Trip.of(Destination.of("Poznan", "Poland"), TransportType.TRAIN, 110.00, 280));

    System.out.println("Best trip for " + adam.getName());
    System.out.println(buildTripDescriptionForUser(tripService, adam));
    System.out.println("Best trip for " + ewa.getName());
    System.out.println(buildTripDescriptionForUser(tripService, ewa));
    System.out.println("Best trip for " + marek.getName());
    System.out.println(buildTripDescriptionForUser(tripService, marek));
    System.out.println("Best trip for " + kasia.getName());
    System.out.println(buildTripDescriptionForUser(tripService, kasia));
    System.out.println("Best trip for " + tomek.getName());
    System.out.println(buildTripDescriptionForUser(tripService, tomek));

    System.out.println("Is there a trip to Rome?");
    System.out.println(describeTripToDestination(tripService, "Rome"));

    System.out.println("Is there a trip to Warsaw?");
    System.out.println(describeTripToDestination(tripService, "Warsaw"));
  }

  private static String buildTripDescriptionForUser(TripService tripService, User user) {
    return tripService.findBestTrip(user)
        .map(Trip::toString)
        .orElse(NOT_FOUND_TRIP);
  }

  private static String describeTripToDestination(TripService tripService, String destination) {
    return tripService.findTripByDestination(destination)
        .map(Trip::toString)
        .orElse(NOT_FOUND_TRIP);
  }
}