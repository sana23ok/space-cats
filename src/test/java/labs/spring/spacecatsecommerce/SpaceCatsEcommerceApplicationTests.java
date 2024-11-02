package labs.spring.spacecatsecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
@ActiveProfiles("test")  // Вказує Spring використовувати профіль "test" та налаштування з application-test.yml
@Testcontainers
class SpaceCatsEcommerceApplicationTests {

    // Налаштування PostgreSQL контейнера для тестів
    @Container
    private static PostgreSQLContainer<?> postgresDB = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("mydatabase")
            .withUsername("myuser")
            .withPassword("secret");

    // Динамічне налаштування параметрів підключення до контейнера
    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresDB::getJdbcUrl);
        registry.add("spring.datasource.username", postgresDB::getUsername);
        registry.add("spring.datasource.password", postgresDB::getPassword);
    }

    @Test
    void contextLoads() {
    }
}
