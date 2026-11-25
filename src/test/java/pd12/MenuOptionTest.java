package pd12;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MenuOptionTest {

  @Test
  void shouldReturnAddOrderWhenNumberIs1() {
    int number = 1;

    Optional<MenuOption> result = MenuOption.fromNumber(number);

    assertThat(result).contains(MenuOption.ADD_ORDER);
  }

  @Test
  void shouldReturnExitWhenNumberIs0() {
    int number = 0;

    Optional<MenuOption> result = MenuOption.fromNumber(number);

    assertThat(result).contains(MenuOption.EXIT);
  }

  @Test
  void shouldReturnRunSimulationWhenNumberIs9() {
    int number = 9;

    Optional<MenuOption> result = MenuOption.fromNumber(number);

    assertThat(result).contains(MenuOption.RUN_SIMULATION);
  }

  @Test
  void shouldReturnEmptyWhenNumberIsBetweenDefinedOptions() {
    int number = 5;

    Optional<MenuOption> result = MenuOption.fromNumber(number);

    assertThat(result).isEmpty();
  }

  @Test
  void shouldReturnEmptyWhenNumberIsNegative() {
    int number = -1;

    Optional<MenuOption> result = MenuOption.fromNumber(number);

    assertThat(result).isEmpty();
  }
}
