package de.rieckpil.blog.exercise9;

import de.rieckpil.blog.todos.Todo;
import de.rieckpil.blog.todos.TodosClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(TodosClient.class)
class TodosClientTest {

  @Autowired
  private MockRestServiceServer server;

  @Autowired
  private TodosClient todosClient;

  // mock一个http返回,返回数据为一个任务
  @Test
  void shouldReturnListOfTodos() {
    this.server
      .expect(requestTo("/todos"))
      .andRespond(withSuccess("[{" +
        "\"id\": 1," +
        "\"userId\": 42, " +
        "\"title\":\"Learn Testing Spring Boot Applications\", " +
        "\"completed\": false" +
        "}]", MediaType.APPLICATION_JSON));

    List<Todo> result = todosClient.fetchAllTodos();

    assertEquals(1, result.size());
  }

  // mock一个http返回,返回数据为ServerError
  @Test
  void shouldPropagateFailure() {
    this.server
      .expect(requestTo("/todos"))
      .andRespond(withServerError());

    assertThrows(RuntimeException.class, () -> {
      todosClient.fetchAllTodos();
    });
  }
}
