package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping (path = "/people")
        public void showAllPersons (@RequestBody Map <String, Object> persons) {
            String query = "SELECT * FROM person";
            jdbc.update(query, persons.get("first_name"), persons.get("last_name"));}

        @GetMapping("/people/{id}")
            public void getPerson (@RequestBody Map <String, Object> person) {
            String query = "SELECT * FROM person WHERE id = (?)"; // check
            jdbc.update(query, person.get("first_name"), person.get("last_name"));}
    // END
}
