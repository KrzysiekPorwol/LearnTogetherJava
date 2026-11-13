package pd10.model;

import java.util.function.Predicate;

public final class TaskCriteria {

  private TaskCriteria() {
  }

  public static Predicate<Task> active() {
    return task -> task.getStatus() != TaskStatus.DONE;
  }

  public static Predicate<Task> closed() {
    return task -> task.getStatus() == TaskStatus.DONE;
  }

  public static Predicate<Task> unassigned() {
    return task -> task.getEmployee() == null;
  }

  public static Predicate<Task> highPriority() {
    return task -> task.getPriority() == TaskPriority.HIGH
        || task.getPriority() == TaskPriority.CRITICAL;
  }

  public static Predicate<Task> hasStatus(TaskStatus status) {
    if (status == null) {
      throw new IllegalArgumentException("status cannot be null");
    }
    return task -> task.getStatus() == status;
  }

  public static Predicate<Task> hasCategory(TaskCategory category) {
    if (category == null) {
      throw new IllegalArgumentException("category cannot be null");
    }
    return task -> task.getCategory() == category;
  }

  public static Predicate<Task> assignedTo(Employee employee) {
    if (employee == null) {
      throw new IllegalArgumentException("employee cannot be null");
    }
    return task -> employee.equals(task.getEmployee());
  }
}