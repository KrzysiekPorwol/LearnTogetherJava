package pd15.mapper;

import org.junit.jupiter.api.Test;
import pd15.domain.Todo;
import pd15.dto.TodoResponse;

import static org.assertj.core.api.Assertions.assertThat;

class TodoMapperTest {
  @Test
  void shouldMapIdAndTitle() {
    TodoResponse todoResponse = new TodoResponse(1L, "Buy milk", true);

    Todo todo = TodoMapper.toDomain(todoResponse);

    assertThat(todo).isEqualTo(new Todo(1L, "Buy milk"));
  }
}
