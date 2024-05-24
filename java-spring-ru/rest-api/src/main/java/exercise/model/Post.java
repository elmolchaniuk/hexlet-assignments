package exercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@NoArgsConstructor
@Setter
@Getter
public class Post {
    private int userId;
    private String slug;
    private String title;
    private String body;
}
