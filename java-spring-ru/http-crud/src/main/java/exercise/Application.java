package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //Список всех постов
    @GetMapping("/posts")
    public List<Post> list (@RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().limit(limit).toList();
    }

    //Просмотр конкретного поста
    @GetMapping("/posts/{id}")
    public Optional<Post> showPost (@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return post;
    }

    //Создание нового поста
    @PostMapping("/posts")
    public Post createNewPost (@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    //Обновление поста
    @PutMapping("/posts/{id}")
    public Post updatePost (@PathVariable String id, @RequestBody Post data) {
        var postToUpdate = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (postToUpdate.isPresent()) {
            var post = postToUpdate.get();
            post.setId(data.getId());
            post.setBody(data.getBody());
            post.setTitle(data.getTitle());
        }
        return data;
    }

    //Удаление поста
    @DeleteMapping("/posts/{id}")
    public void deletePost (@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }

}
