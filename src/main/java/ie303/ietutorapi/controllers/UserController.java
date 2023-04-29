package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.User;
import ie303.ietutorapi.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    private InstructorService instructorService;

    // Get all instructors
    @GetMapping("/instructors")
    public List<User> getInstructors() {
        // Get all user that have the role of 1 (instructor) from MongoDB databas
        return instructorService.getAllInstructors();
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
