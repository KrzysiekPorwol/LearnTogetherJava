package pd10.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Employee {
  private final String firstName;
  private final String lastName;
  private final String pesel;

  public static Employee create(String firstName, String lastName, String pesel) {
    if (firstName == null || firstName.isBlank()) {
      throw new IllegalArgumentException("First name cannot be empty");
    }
    if (lastName == null || lastName.isBlank()) {
      throw new IllegalArgumentException("Last name cannot be empty");
    }
    if (pesel == null || !pesel.matches("\\d{11}")) {
      throw new IllegalArgumentException("PESEL must contain exactly 11 digits");
    }
    return new Employee(firstName, lastName, pesel);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(pesel, employee.pesel);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(pesel);
  }

  @Override
  public String toString() {
    return firstName + " " + lastName + " (PESEL: " + pesel + ")";
  }
}