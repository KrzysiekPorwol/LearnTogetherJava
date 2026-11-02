package pd10;

import pd10.model.*;
import pd10.printer.TaskReportPrinter;
import pd10.repository.EmployeeRepository;
import pd10.repository.TaskRepository;
import pd10.service.TaskReportService;
import pd10.service.TaskService;

import java.util.Comparator;
import java.util.List;

public class TaskApp {

  public static void main(String[] args) {
    TaskRepository taskRepository = new TaskRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    TaskService taskService = new TaskService(taskRepository, employeeRepository);
    TaskReportService reportService = new TaskReportService(taskRepository, employeeRepository);
    TaskReportPrinter printer = new TaskReportPrinter();

    taskService.registerEmployee("Jan", "Kowalski", "90010112345");
    taskService.registerEmployee("Anna", "Nowak", "85050554321");

    long loginBug = taskService.registerTask("Login fails", "Users cannot log in after update",
        TaskCategory.BUG_FIXES, TaskPriority.CRITICAL);
    long darkMode = taskService.registerTask("Dark mode", "Add dark theme to settings",
        TaskCategory.NEW_FEATURES, TaskPriority.LOW);
    long slowQuery = taskService.registerTask("Slow report query", "Report page loads 20 seconds",
        TaskCategory.OPTIMIZATION, TaskPriority.HIGH);

    taskService.assignEmployee(loginBug, "90010112345");
    taskService.assignEmployee(darkMode, "85050554321");
    taskService.start(loginBug);
    taskService.end(loginBug, "Session cookie path fixed");

    System.out.println(taskService.getTaskOrThrow(loginBug));
    System.out.println();

    try {
      taskService.start(slowQuery);
    } catch (IllegalStateException e) {
      System.out.println("Cannot start task: " + e.getMessage());
    }

    try {
      taskService.assignEmployee(loginBug, "85050554321");
    } catch (IllegalStateException e) {
      System.out.println("Cannot change closed task: " + e.getMessage());
    }

    System.out.println("\nOpen tasks:");
    List<Task> openTasks = taskService.findTasks(TaskCriteria.hasStatus(TaskStatus.OPEN));
    for (Task task : openTasks) {
      System.out.println(task.getId() + " " + task.getTitle());
    }

    Employee anna = employeeRepository.findByPesel("85050554321").orElseThrow();
    System.out.println("\nAnna's tasks:");
    for (Task task : taskService.findTasks(TaskCriteria.assignedTo(anna))) {
      System.out.println(task.getId() + " " + task.getTitle());
    }

    System.out.println("\nActive tasks sorted by priority:");
    List<Task> sorted = taskService.findTasks(TaskCriteria.active()).stream()
        .sorted(Comparator.comparing(Task::getPriority))
        .toList();
    for (Task task : sorted) {
      System.out.println(task.getPriority() + " " + task.getTitle());
    }

    System.out.println();
    printer.print(reportService.generate());
  }
}
