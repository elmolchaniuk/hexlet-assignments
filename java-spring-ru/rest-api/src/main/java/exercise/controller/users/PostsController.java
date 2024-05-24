package exercise.controller.users;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api")
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable Integer id) {
        var result = posts.stream().filter(p -> p.getUserId() == id).toList();

        return ResponseEntity.of(Optional.ofNullable(result));
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer id) {
        if (post.getSlug() == null || post.getTitle() == null || post.getBody() == null) {
            return ResponseEntity.badRequest().body(post);
        }

        post.setUserId(id);
        post.setTitle(post.getTitle());
        post.setBody(post.getBody());
        post.setSlug(post.getSlug());
        posts.add(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
