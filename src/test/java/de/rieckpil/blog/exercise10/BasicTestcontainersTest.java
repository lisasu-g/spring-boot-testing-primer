package de.rieckpil.blog.exercise10;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class BasicTestcontainersTest {

  // 基本的数据库联通测试
  // Write a test that starts a Docker container using a
  // Testcontainers module (e.g., a PostgreSQL database)

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer =
    new PostgreSQLContainer<>(DockerImageName.parse("postgres:13"))
    .withDatabaseName("test")
    .withUsername("duke")
    .withPassword("superSecret");

  @Test
  void shouldStartPostgreSQLDatabase() {
    assertTrue(postgreSQLContainer.isRunning());
  }
}
