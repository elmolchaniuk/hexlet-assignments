package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getAllPosts() {
        var users = postRepository.findAll();
        var result = users.stream()
                .map(this::toPostDTO)
                .toList();
        return result;
    }

    @GetMapping(path = "/{id}")
    public PostDTO getPost(@PathVariable Long id) {
        var post =  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        var result = toPostDTO(post);
        return result;
    }


    private PostDTO toPostDTO(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setComments(toCommentDto(commentRepository.findByPostId(post.getId())));
        return dto;
    }

    private List<CommentDTO> toCommentDto(List<Comment> comments) {
     var dtoList = new ArrayList<CommentDTO>();
     for (Comment comment : comments) {
         var dto = new CommentDTO();
         dto.setId(comment.getId());
         dto.setBody(comment.getBody());
         dtoList.add(dto);
     }
     return dtoList;
    }
}
