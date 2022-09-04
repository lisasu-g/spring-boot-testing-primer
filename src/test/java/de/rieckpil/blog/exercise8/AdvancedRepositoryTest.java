package de.rieckpil.blog.exercise8;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AdvancedRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void shouldReturnCustomerThatJoinedTheEarliest() {

    // 删除了所有的用户
    customerRepository.deleteAll();

    Customer customerOne = new Customer("duke", ZonedDateTime.now());
    Customer customerTwo = new Customer("anna", ZonedDateTime.now().minusMinutes(42));
    Customer customerThree = new Customer("mike", ZonedDateTime.now().minusDays(42));

    customerRepository.saveAll(List.of(customerOne, customerTwo, customerThree));
    // 存储了三个新用户

    // 测试真正的方法 早鸟
    Customer result = customerRepository.getEarlyBird();

    // 早鸟的名字
    assertEquals("mike", result.getName());
  }
}
