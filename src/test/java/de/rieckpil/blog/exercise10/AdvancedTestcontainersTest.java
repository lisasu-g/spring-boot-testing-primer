package de.rieckpil.blog.exercise10;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class AdvancedTestcontainersTest {
    // keycloak是一个开源软件产品，允许单点登录与身份和访问管理的目标是现代应用程序和服务。
    // 这个复杂环境的docker测试

    //Advanced: Use the GenericContainer class from Testcontainers to start
    // a custom Docker image (make sure to add a health check strategy)

    @Container
    static GenericContainer<?> keycloakContainer =
            new GenericContainer<>(DockerImageName.parse("jboss/keycloak:14.0.0"))
                    .withExposedPorts(8080)
                    .withStartupTimeout(Duration.ofSeconds(30))
                    .waitingFor(Wait.forHttp("/auth").forStatusCode(200));

    @Test
    void shouldStartCustomDockerContainer() {
        assertTrue(keycloakContainer.isRunning());
    }
}
