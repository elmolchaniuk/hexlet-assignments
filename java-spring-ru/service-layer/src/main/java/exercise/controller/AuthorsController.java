package exercise.controller;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AuthorDTO>> index() {
        var authors = authorService.getAll();

        return ResponseEntity.ok()
                .body(authors);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> show(@PathVariable long id) {
        var author = authorService.findById(id);

        return ResponseEntity.ok()
                .body(author);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDTO> create(@Valid @RequestBody AuthorCreateDTO authorData) {
        var author = authorService.create(authorData);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(author);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> update(@PathVariable long id, @RequestBody @Valid AuthorUpdateDTO authorData) {
        var author = authorService.update(authorData, id);

        return ResponseEntity.ok()
                .body(author);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        authorService.delete(id);
    }
}
