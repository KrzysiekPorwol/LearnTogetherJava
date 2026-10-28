package pd7_1.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import pd7_1.model.TransportType;
import pd7_1.model.Trip;
import pd7_1.model.User;
import pd7_1.repository.TripRepository;

import java.util.Optional;
@RequiredArgsConstructor
public class TripService {

  private final TripRepository tripRepository;

  public Optional<Trip> findBestTrip(User user) {

    TransportType preferredTransport = user.getPreferredTransport();
    Double budget = user.getBudget();

    if (ObjectUtils.allNull(preferredTransport, budget)) {
      return Optional.empty();
    }
    return tripRepository.findAll().stream()
        .filter(trip -> preferredTransport == null || trip.getTransportType() == preferredTransport)
        .filter(trip -> budget == null || trip.getPrice() <= budget)
        .findFirst();
  }

  public Optional<Trip> findTripByDestination(String destination) {
    return tripRepository.findByDestination(destination);
  }
}
