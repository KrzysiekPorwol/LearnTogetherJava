package pd10.service;

import lombok.RequiredArgsConstructor;
import pd10.exception.EmployeeNotFoundException;
import pd10.exception.TaskNotFoundException;
import pd10.model.Employee;
import pd10.model.Task;
import pd10.model.TaskCategory;
import pd10.model.TaskPriority;
import pd10.repository.EmployeeRepository;
import pd10.repository.TaskRepository;

import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final EmployeeRepository employeeRepository;

  public long registerTask(String title, String description,
                           TaskCategory category, TaskPriority priority) {
    Task newTask = Task.create(title, description, category, priority);
    taskRepository.add(newTask);
    return newTask.getId();
  }

  public void registerEmployee(String firstName, String lastName, String pesel) {
    Employee newEmployee = Employee.create(firstName, lastName, pesel);
    employeeRepository.add(newEmployee);
  }

  public void assignEmployee(long taskId, String pesel) {
    Task task = requireTask(taskId);
    Employee employee = employeeRepository.findByPesel(pesel)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee with given PESEL not found"));

    task.assignEmployee(employee);
  }

  public void start(long taskId) {
    Task task = requireTask(taskId);
    task.start();
  }

  public void end(long taskId, String resolution) {
    Task task = requireTask(taskId);
    task.end(resolution);
  }

  public Task getTaskById(long taskId) {
    return requireTask(taskId);
  }

  private Task requireTask(long taskId) {
    return taskRepository.findById(taskId)
        .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));
  }

  public List<Task> findTasks(Predicate<Task> criteria) {
    if (criteria == null) {
      throw new IllegalArgumentException("criteria cannot be null");
    }
    return taskRepository.findAll(criteria);
  }
}