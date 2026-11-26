package pd15.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd15.dto.TodoResponse;

import java.util.List;

public class TodoResponseTest {
  public static void main(String[] args) throws JsonProcessingException {
    String json = """
        [
            {
                "userId": 1,
                "id": 1,
                "title": "delectus aut autem",
                "completed": false
            },
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            },
            {
                "userId": 1,
                "id": 3,
                "title": "fugiat veniam minus",
                "completed": false
            },
            {
                "userId": 1,
                "id": 4,
                "title": "et porro tempora",
                "completed": true
            },
            {
                "userId": 1,
                "id": 5,
                "title": "missing completed field"
            },
            {
                "userId": 1,
                "id": 6,
                "title": "null completed field",
                "completed": null
            }
        ]
        """;

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    List<TodoResponse> todos = objectMapper.readValue(json, new TypeReference<List<TodoResponse>>() {});
    todos.forEach(System.out::println);
  }
}
