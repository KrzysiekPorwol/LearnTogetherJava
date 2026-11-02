package pd10.service;

import lombok.RequiredArgsConstructor;
import pd10.model.Employee;
import pd10.model.Task;
import pd10.model.TaskCriteria;
import pd10.model.TaskReport;
import pd10.repository.EmployeeRepository;
import pd10.repository.TaskRepository;

import java.util.*;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class TaskReportService {

  private final TaskRepository taskRepository;
  private final EmployeeRepository employeeRepository;

  public TaskReport generate() {
    List<Task> tasks = taskRepository.findAll();

    long open = 0;
    long inProgress = 0;
    long done = 0;

    long newFeatures = 0;
    long bugFixes = 0;
    long refactoring = 0;
    long optimization = 0;

    Map<Employee, Long> activeByEmployee = new LinkedHashMap<>();
    for (Employee employee : employeeRepository.findAll()) {
      activeByEmployee.put(employee, 0L);
    }

    List<Task> unassignedHighPriority = new ArrayList<>();

    Predicate<Task> activeAndAssigned = TaskCriteria.active()
        .and(TaskCriteria.unassigned().negate());
    Predicate<Task> unassignedAndHighPriority = TaskCriteria.unassigned()
        .and(TaskCriteria.highPriority());

    for (Task task : tasks) {
      switch (task.getStatus()) {
        case OPEN -> open++;
        case IN_PROGRESS -> inProgress++;
        case DONE -> done++;
      }

      switch (task.getCategory()) {
        case NEW_FEATURES -> newFeatures++;
        case BUG_FIXES -> bugFixes++;
        case REFACTORING -> refactoring++;
        case OPTIMIZATION -> optimization++;
      }

      if (activeAndAssigned.test(task)) {
        activeByEmployee.merge(task.getEmployee(), 1L, Long::sum);
      }

      if (unassignedAndHighPriority.test(task)) {
        unassignedHighPriority.add(task);
      }
    }

    return TaskReport.builder()
        .openCount(open)
        .inProgressCount(inProgress)
        .doneCount(done)
        .newFeaturesCount(newFeatures)
        .bugFixesCount(bugFixes)
        .refactoringCount(refactoring)
        .optimizationCount(optimization)
        .activeTasksByEmployee(Collections.unmodifiableMap(activeByEmployee))
        .unassignedHighPriorityTasks(List.copyOf(unassignedHighPriority))
        .allTasksClosed(done == tasks.size())
        .build();
  }
}
