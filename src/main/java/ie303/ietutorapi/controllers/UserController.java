package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.User;
import ie303.ietutorapi.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepo;

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
}
