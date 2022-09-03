package de.rieckpil.blog.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// 数据库操作类
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query(value =
    "SELECT * " +
      "FROM customer " +
      "ORDER BY joined_at ASC " +
      "LIMIT 1", nativeQuery = true)
  Customer getEarlyBird();
}
