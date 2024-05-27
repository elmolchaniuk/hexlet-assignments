package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    @Autowired
    private UserProperties admins;

    @GetMapping("/admins")
    public List<String> getAdmins() {
        List<String> emails = admins.getAdmins();
        List<User> users1 = users.stream()
                .filter(u -> u.getEmail().equals(emails.get(0)))
                .toList();
        List<User> users2 = users.stream()
                .filter(u -> u.getEmail().equals(emails.get(1)))
                .toList();
        List<User> users3 = users.stream()
                .filter(u -> u.getEmail().equals(emails.get(2)))
                .toList();

        String name1 = users1.get(0).getName();
        String name2 = users2.get(0).getName();
        String name3 = users3.get(0).getName();

        List<String> nameOfAdmins = new ArrayList<>();
        nameOfAdmins.add(0, name1);
        nameOfAdmins.add(1, name2);
        nameOfAdmins.add(2, name3);

        nameOfAdmins.sort(Comparator.naturalOrder());
        return nameOfAdmins;
    }

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
