package de.rieckpil.blog.exercise11;

import de.rieckpil.blog.customer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

// Write an integration test to access an API endpoint
// while using the TestRestTemplate and @SpringBootTest

// 模拟了一个登录的用户名字和密码
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
  "spring.security.user.name=duke",
  "spring.security.user.password=duke42"
})
class BasicIT {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void shouldFetchListOfCustomers() {
    // 设置了http头
    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth("duke", "duke42");

    HttpEntity<List<Customer>> request = new HttpEntity<>(headers);

    // get请求这个url,使用的上面定义的http头
    ResponseEntity<List<Customer>> result = this.testRestTemplate
      .exchange("/api/customers", HttpMethod.GET, request, new ParameterizedTypeReference<>() {});

    assertEquals(200, result.getStatusCodeValue());
  }
}
