package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.User;
import ie303.ietutorapi.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    @Autowired

    private UserRepository userRepo;
    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> user = userRepo.findAll();
        return user;
    }

    // Get all instructors
    @GetMapping("/instructors")
    public List<User> getInstructors() {
        // Get all user that have the role of 1 (instructor) from MongoDB databas
        return null;
    }

    @GetMapping("/user/{email}")
    public Optional<User> getUserByID(@PathVariable String email) {
        Optional<User> user = userRepo.findUsersByEmail(email);
        return user;
    }

    @GetMapping("/userId/{id}")
    public Optional<User> getUseruserID(@PathVariable String id) {
        Optional<User> user = userRepo.findUsersById(id);
        return user;
    }


    // Change user's role to instructor
    @PutMapping("/users/{id}/become-instructor")
    public ResponseEntity<?> becomeInstructor(@PathVariable("id") ObjectId id) {
        // find the user by id
        User user = userRepo.findById(String.valueOf(id)).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        // update the user's role
        user.setRole(1);
        userRepo.save(user);

        return ResponseEntity.ok(user);
    }

    //Add new user
    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    //Delete user by ID
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
