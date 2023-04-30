package ie303.ietutorapi.controllers;

import ie303.ietutorapi.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepo;

    // Get all subjects and return them as a JSON object
    @GetMapping("/subjects")
    public ResponseEntity<?> getAllSubjects() {
        // Get all subjects from MongoDB database
        var subjects = subjectRepo.findAll();

        if (subjects.isEmpty()) {
            // return json object with error message
            return ResponseEntity.badRequest().body("No subjects found");
        }

        return ResponseEntity.ok(subjects);
    }
}