package pd7_1.repository;

import pd7_1.model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripRepository {

  private final List<Trip> tripList = new ArrayList<>();

  public void add(Trip trip) {
    tripList.add(trip);
  }

  public List<Trip> findAll() {
    return new ArrayList<>(tripList);
  }

  public Optional<Trip> findByDestination(String destination) {
    for (Trip t : tripList) {
      if (destination.trim().equals(t.getDestination().getName())) {
        return Optional.of(t);
      }
    }
    return Optional.empty();
  }
}