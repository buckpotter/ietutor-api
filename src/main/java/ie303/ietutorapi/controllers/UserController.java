package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.User;
import ie303.ietutorapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    // Get all instructors
    @GetMapping("/instructors")
    public List<User> getInstructors() {
        // Get all user that have the role of 1 (instructor) from MongoDB databas
        return null;
    }
    @GetMapping("/user/{email}")
    public Optional<User> getUserByID(@PathVariable String email)
    {
        Optional<User> user= userRepository.findUsersByEmail(email);
        return user;
    }
    @GetMapping("/userId/{id}")
    public Optional<User> getUseruserID(@PathVariable String id)
    {
        Optional<User> user= userRepository.findUsersById(id);
        return user;
    }

    // Get instructor by id
    @GetMapping("/instructors/{_id}")
    public String getInstructorById(@PathVariable String _id) {
        return "Hello World";
    }

    // Save instructor
    @PostMapping("/instructors")
    public String saveInstructor(@RequestBody User user) {
        return "Hello World " + user;
    }
}
