package pd10.repository;

import pd10.model.Task;

import java.util.*;
import java.util.function.Predicate;

public class TaskRepository {

  private long nextId = 0L;
  private final Map<Long, Task> tasks = new HashMap<>();

  public void add(Task task) {
    if (task == null) {
      throw new IllegalArgumentException("task cannot be null");
    }
    task.assignId(nextId);
    tasks.put(task.getId(), task);
    nextId++;
  }

  public Optional<Task> findById(long id) {
    return Optional.ofNullable(tasks.get(id));
  }

  public List<Task> findAll(Predicate<Task> criteria) {
    return tasks.values().stream()
        .filter(criteria)
        .toList();
  }
}
