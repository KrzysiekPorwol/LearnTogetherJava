package pd10.printer;

import pd10.model.Employee;
import pd10.model.Task;
import pd10.model.TaskReport;

public class TaskReportPrinter {

  public void print(TaskReport report) {
    System.out.println("!- TASK REPORT -!");

    System.out.println("\nTasks by status:");
    System.out.println("OPEN: " + report.getOpenCount());
    System.out.println("IN_PROGRESS: " + report.getInProgressCount());
    System.out.println("DONE: " + report.getDoneCount());

    System.out.println("\nTasks by category:");
    System.out.println("NEW_FEATURES: " + report.getNewFeaturesCount());
    System.out.println("BUG_FIXES: " + report.getBugFixesCount());
    System.out.println("REFACTORING: " + report.getRefactoringCount());
    System.out.println("OPTIMIZATION: " + report.getOptimizationCount());

    System.out.println("\nActive tasks per employee:");
    for (Employee employee : report.getActiveTasksByEmployee().keySet()) {
      long count = report.getActiveTasksByEmployee().get(employee);
      System.out.println(employee.getFirstName() + " " + employee.getLastName() + ": " + count);
    }

    System.out.println("\nUnassigned high priority tasks:");
    for (Task task : report.getUnassignedHighPriorityTasks()) {
      System.out.println(task.getId() + " " + task.getTitle() + " " + task.getPriority());
    }

    System.out.println("\nAll tasks closed: " + (report.isAllTasksClosed() ? "YES" : "NO"));
  }
}
