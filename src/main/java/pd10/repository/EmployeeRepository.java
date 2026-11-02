package pd10.repository;

import pd10.exception.EmployeeAlreadyExistException;
import pd10.model.Employee;

import java.util.*;

public class EmployeeRepository {

  private final Map<String, Employee> employees = new HashMap<>();

  public void add(Employee employee) {
    if (employee == null) {
      throw new IllegalArgumentException("employee cannot be null");
    }
    if (employees.containsKey(employee.getPesel())) {
      throw new EmployeeAlreadyExistException("employee with this PESEL already exists");
    }
    employees.put(employee.getPesel(), employee);
  }

  public Optional<Employee> findByPesel(String pesel) {
    return Optional.ofNullable(employees.get(pesel));
  }

  public List<Employee> findAll() {
    return new ArrayList<>(employees.values());
  }

}
