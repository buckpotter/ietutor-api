package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.Plan;
import ie303.ietutorapi.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PlanController {
    @Autowired
    private PlanRepository planRepo;

    // get all plans
    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> getAllPlans() {
        // Get all plans from MongoDB database
        return ResponseEntity.ok(planRepo.findAll());
    }
}
