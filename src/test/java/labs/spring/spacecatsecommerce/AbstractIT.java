package labs.spring.spacecatsecommerce;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public abstract class AbstractIT {

    private static final int POSTGRES_PORT = 5432;

    static final GenericContainer<?> POSTGRES_CONTAINER = new GenericContainer<>(DockerImageName.parse("postgres:15.6-alpine"))
            .withEnv("POSTGRES_PASSWORD", "postgres")
            .withEnv("POSTGRES_DB", "postgres")
            .withExposedPorts(POSTGRES_PORT);

    static {
        POSTGRES_CONTAINER.start();
    }

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .configureStaticDsl(true)
            .build();

    @DynamicPropertySource
    static void setupTestContainerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> format("jdbc:postgresql://%s:%d/postgres",
                POSTGRES_CONTAINER.getHost(), POSTGRES_CONTAINER.getMappedPort(POSTGRES_PORT)));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "postgres");
        registry.add("application.payment-service.base-path", wireMockServer::baseUrl);

        WireMock.configureFor(wireMockServer.getPort());
    }
}
