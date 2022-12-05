package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String makeGetRequest () {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String makeHelloRequest (@RequestParam (value = "name", defaultValue = "World") String name) {
        return String.format("Welcome", name, "!");
    }
}

// END
