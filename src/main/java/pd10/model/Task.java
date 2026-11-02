package pd10.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Task {

  private static final int MIN_RESOLUTION_LENGTH = 10;

  private Long id;
  private final String title;
  private final String description;
  private final TaskCategory category;
  private final TaskPriority priority;
  private TaskStatus status = TaskStatus.OPEN;
  private Employee employee;
  private String resolution;

  public static Task create(String title, String description,
                            TaskCategory category, TaskPriority priority) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be empty");
    }
    if (category == null) {
      throw new IllegalArgumentException("Category is required");
    }
    if (priority == null) {
      throw new IllegalArgumentException("Priority is required");
    }
    return new Task(title, description, category, priority);
  }

  public void assignId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id cannot be null");
    }
    if (this.id != null) {
      throw new IllegalStateException("this task already has an ID");
    }
    this.id = id;
  }

  public void assignEmployee(Employee employee) {
    ensureNotClosed();
    if (employee == null) {
      throw new IllegalArgumentException("Employee cannot be null");
    }
    this.employee = employee;
  }

  public void start() {
    ensureIsOpen();
    if (this.employee == null) {
      throw new IllegalStateException("doesn't have an employee");
    }
    this.status = TaskStatus.IN_PROGRESS;
  }

  public void end(String resolution) {
    ensureIsProgress();
    if (resolution == null || resolution.isBlank()) {
      throw new IllegalArgumentException("Resolution cannot be empty");
    }
    if (resolution.length() < MIN_RESOLUTION_LENGTH) {
      throw new IllegalArgumentException("resolution must be longer than " + MIN_RESOLUTION_LENGTH);
    }
    this.resolution = resolution;
    this.status = TaskStatus.DONE;
  }

  private void ensureNotClosed() {
    if (this.status == TaskStatus.DONE) {
      throw new IllegalStateException("This task is already closed");
    }
  }

  private void ensureIsOpen() {
    if (this.status != TaskStatus.OPEN) {
      throw new IllegalStateException("This task isn't open");
    }
  }

  private void ensureIsProgress() {
    if (this.status != TaskStatus.IN_PROGRESS) {
      throw new IllegalStateException("This task isn't In Progress");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return Objects.equals(id, task.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return """
        Task #%s: %s
          Description: %s
          Category:    %s
          Priority:    %s
          Status:      %s
          Employee:    %s
          Resolution:  %s""".formatted(
        id,
        title,
        description,
        category,
        priority,
        status,
        employee != null ? employee : "unassigned",
        resolution != null ? resolution : "-"
    );
  }
}