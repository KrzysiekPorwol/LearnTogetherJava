package pd10.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class TaskReport {

  private final long openCount;
  private final long inProgressCount;
  private final long doneCount;

  private final long newFeaturesCount;
  private final long bugFixesCount;
  private final long refactoringCount;
  private final long optimizationCount;

  private final Map<Employee, Long> activeTasksByEmployee;
  private final List<Task> unassignedHighPriorityTasks;
  private final boolean allTasksClosed;
}