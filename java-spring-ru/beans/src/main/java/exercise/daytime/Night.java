package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Night implements Daytime {

    @Override
    public String getName() {
        return "night";
    }

    @PostConstruct
    public void init() {
        System.out.println("Night bean created");
    }
}
