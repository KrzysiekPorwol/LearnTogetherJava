package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Todo;
import pd15.dto.TodoResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TodoMapper {

  public static Todo toDomain(TodoResponse todoResponse) {
    return new Todo(todoResponse.id(), todoResponse.title());
  }
}