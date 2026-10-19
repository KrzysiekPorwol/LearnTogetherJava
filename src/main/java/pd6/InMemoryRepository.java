package pd6;

import java.util.*;

public class InMemoryRepository<ID, T extends Entity<ID>> {

  private final Map<ID, T> storage = new HashMap<>();

  public void save(T entity) {
    storage.put(entity.getId(), entity);
  }

  public Optional<T> findById(ID id) {
    return Optional.ofNullable(storage.get(id));
  }

  public void deleteById(ID id) {
    storage.remove(id);
  }

  public List<T> findAll() {
    return new ArrayList<>(storage.values());
  }
}