package ie303.ietutorapi.controllers;

import ie303.ietutorapi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepo;
}
