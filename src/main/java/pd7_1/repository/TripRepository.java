package pd7_1.repository;

import org.apache.commons.lang3.StringUtils;
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
    if (StringUtils.isBlank(destination)) {
      return Optional.empty();
    }
    String searchedName = destination.trim();
    for (Trip t : tripList) {
      if (searchedName.equals(t.getDestination().getName())) {
        return Optional.of(t);
      }
    }
    return Optional.empty();
  }
}