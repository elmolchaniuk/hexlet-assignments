package exercise.daytime;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Day implements Daytime {

    @Override
    public String getName() {
        return "day";
    }

    @PostConstruct
    public void init() {
        System.out.println("Day bean created");
    }

}
