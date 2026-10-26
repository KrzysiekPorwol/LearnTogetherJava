package pd7_1.service;

import pd7_1.formatter.TripFormatter;
import pd7_1.model.TransportType;
import pd7_1.model.Trip;
import pd7_1.model.User;
import pd7_1.repository.TripRepository;

import java.util.Optional;

public class TripService {

  private static final String NOT_FOUND_TRIP = "No trip available";

  private final TripRepository tripRepository;

  public TripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  public Optional<Trip> findBestTrip(User user) {

    TransportType preferredTransport = user.getPreferredTransport();
    Double budget = user.getBudget();

    if (preferredTransport == null && budget == null) {
      return Optional.empty();
    }
    return tripRepository.findAll().stream()
        .filter(trip -> preferredTransport == null || trip.getTransportType() == preferredTransport)
        .filter(trip -> budget == null || trip.getPrice() <= budget)
        .findFirst();
  }

  public String buildTripDescriptionForUser(User user) {
    Optional<Trip> optionalTrip = findBestTrip(user);
    return optionalTrip
        .map(TripFormatter::writeTripDescription)
        .orElse(NOT_FOUND_TRIP);
  }

  public String buildTripDescriptionForDestination(String destination) {
    return tripRepository.findByDestination(destination)
        .map(TripFormatter::writeTripDescription)
        .orElse(NOT_FOUND_TRIP);
  }
}
