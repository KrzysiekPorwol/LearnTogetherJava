package pd12;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum MenuOption {
  EXIT(0, "Exit the application"),
  ADD_ORDER(1, "Add an order"),
  SHOW_ORDERS(2, "Show pending orders"),
  PROCESS_ORDERS(3, "Process pending orders"),
  RUN_SIMULATION(9, "Run simulation on sample orders");

  private final int number;
  private final String description;

  public static Optional<MenuOption> fromNumber(int number) {
    for (MenuOption option : values()) {
      if (option.getNumber() == number) {
        return Optional.of(option);
      }
    }
    return Optional.empty();
  }
}
