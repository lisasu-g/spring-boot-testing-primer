package de.rieckpil.blog.exercise8;


import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BasicRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  /**
   * 能够保存JPA实体,并且查询回来
   */
  @Test
  void shouldSaveAndRetrieveJpaEntity() {
    Customer customerToStore = new Customer();
    customerToStore.setName("mike");
    customerToStore.setJoinedAt(ZonedDateTime.now());

    // 将其存入并且找回
    Customer result = testEntityManager.persistFlushFind(customerToStore);

    assertNotNull(result.getId());
  }
}
