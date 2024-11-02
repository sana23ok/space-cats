package labs.spring.spacecatsecommerce.web.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CatNotFoundExceptionTest {

    @Test
    void catNotFoundException_ShouldContainExpectedMessage() {
        String catName = "Whiskers";
        String expectedMessage = String.format("Sorry %s, that you trying to reach, haven't left greeting for you", catName);

        assertThatThrownBy(() -> {
            throw new CatNotFoundException(catName);
        }).isInstanceOf(CatNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
