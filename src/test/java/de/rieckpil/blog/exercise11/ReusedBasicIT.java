package de.rieckpil.blog.exercise11;

import de.rieckpil.blog.customer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

// Advanced: Write an additional integration test that can reuse
// an already started application context to avoid long test runs
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
  "spring.security.user.name=duke",
  "spring.security.user.password=duke42"
})
class ReusedBasicIT {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void shouldCustomerById() {

    // 因为这里没mock那个repository的返回值,所以还是旧的
    //TODO: 这个1号不知道为啥官方的例子里面是没问题的,但是我的有问题
    //TODO: resources/data.sql 下面的sql好像会自动执行,在测试之前
    //TODO: 和这个文件也有关 application.properties 如果没有这个文件就不会帮忙自动创建数据库的表
    // 问题就是有sql插入语句也会执行失败
    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth("duke", "duke42");

    HttpEntity<Customer> request = new HttpEntity<>(headers);

    ResponseEntity<Customer> result = this.testRestTemplate
      .exchange("/api/customers/1", HttpMethod.GET, request, Customer.class);

    assertEquals(200, result.getStatusCodeValue());
  }
}
