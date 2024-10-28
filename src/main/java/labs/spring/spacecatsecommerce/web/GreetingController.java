package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.config.GreetingProperties;
import labs.spring.spacecatsecommerce.config.GreetingProperties.CatGreeting;
import labs.spring.spacecatsecommerce.web.exception.CatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Optional.ofNullable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/greetings")
public class GreetingController {

    private final GreetingProperties greetingProperties;

    @GetMapping("/{name}")
    public ResponseEntity<String> getCustomerById(@PathVariable String name) {
        String greeting = ofNullable(greetingProperties.getGreetings()
            .get(name)).map(CatGreeting::getMessage).orElseThrow(() -> new CatNotFoundException(name));
        return ResponseEntity.ok(greeting);
    }
}
