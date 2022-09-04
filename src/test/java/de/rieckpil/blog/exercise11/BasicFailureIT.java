package de.rieckpil.blog.exercise11;

import de.rieckpil.blog.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

// Create a new integration test and replace one of the beans with a mock
// to simulate a runtime exception while accessing the database and expect
// the API to return HTTP status code 500

// Hint: Harcode and override the default Spring
// Security user to access the endpoints with basic auth

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
  "spring.security.user.name=duke",
  "spring.security.user.password=duke42"
})
// TODO:为什么这个东西是集成测试?不是单元测试呢??
class BasicFailureIT {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @MockBean
  private CustomerRepository customerRepository;

  @Test
  void shouldFailFetchingCustomersWhenDatabaseIsDown() {

    // 但这个repository使用findall的方法时候,报错数据库挂了
    // 这个数据库挂了影响到我了,所以是集成测试
    when(customerRepository.findAll()).thenThrow(new RuntimeException("Database is down :("));

    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth("duke", "duke42");

    HttpEntity<String> request = new HttpEntity<>(headers);

    ResponseEntity<String> result = this.testRestTemplate
      .exchange("/api/customers", HttpMethod.GET, request, String.class);

    // 这时候应该返回500错误
    assertEquals(500, result.getStatusCodeValue());
  }
}
