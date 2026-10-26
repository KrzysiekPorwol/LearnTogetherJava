package pd7_1;

import pd7_1.model.Destination;
import pd7_1.model.TransportType;
import pd7_1.model.Trip;
import pd7_1.model.User;
import pd7_1.repository.TripRepository;
import pd7_1.service.TripService;

public class App {

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
    System.out.println(tripService.buildTripDescriptionForUser(adam));
    System.out.println("Best trip for " + ewa.getName());
    System.out.println(tripService.buildTripDescriptionForUser(ewa));
    System.out.println("Best trip for " + marek.getName());
    System.out.println(tripService.buildTripDescriptionForUser(marek));
    System.out.println("Best trip for " + kasia.getName());
    System.out.println(tripService.buildTripDescriptionForUser(kasia));
    System.out.println("Best trip for " + tomek.getName());
    System.out.println(tripService.buildTripDescriptionForUser(tomek));

    System.out.println("Is there a trip to Rome?");
    System.out.println(tripService.buildTripDescriptionForDestination("Rome"));

    System.out.println("Is there a trip to Warsaw?");
    System.out.println(tripService.buildTripDescriptionForDestination("Warsaw"));
  }
}