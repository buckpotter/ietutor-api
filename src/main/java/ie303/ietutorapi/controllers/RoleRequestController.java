package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.Notification;
import ie303.ietutorapi.models.RoleRequest;
import ie303.ietutorapi.repositories.NotificationRepository;
import ie303.ietutorapi.repositories.RoleRequestRepository;
import ie303.ietutorapi.repositories.UserRepository;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RoleRequestController {
    @Autowired
    NotificationRepository notificationRepo;
    @Autowired
    private RoleRequestRepository roleRequestRepo;
    @Autowired
    private UserRepository userRepo;

    // Get all role requests and return them as a JSON object
    @GetMapping("/role-requests")
    public ResponseEntity<List<RoleRequest>> getAllRoleRequests() {
        List<RoleRequest> roleRequests = roleRequestRepo.findAll();

        if (roleRequests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(roleRequests, HttpStatus.OK);
    }

    @GetMapping("/role-requests/{userId}")
    public ResponseEntity<List<RoleRequest>> getRoleRequestsByUserId(@PathVariable("userId") ObjectId userId) {
        List<RoleRequest> roleRequests = roleRequestRepo.findAll();

        if (roleRequests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(roleRequests, HttpStatus.OK);
    }

    // Gửi request để trở thành instructor
    @PostMapping("/users/add-instructor-request")
    // requestBody is the JSON object sent from the client
    public ResponseEntity<?> becomeInstructor(@RequestBody Json requestBody) {
        // Save the request to the database
        RoleRequest roleRequest = new RoleRequest();
        roleRequest.setUserId(requestBody.userId);
        roleRequest.setHourlyWage(requestBody.hourlyWage);
        roleRequest.setApproved(false);
        // created_at is set automatically by MongoDB
        roleRequest.setCreatedAt(new java.util.Date());
        roleRequestRepo.save(roleRequest);

        // return the role request object with status code 200
        return ResponseEntity.ok(roleRequest);
    }

    // when the admin approves the request. The request is deleted from the database and the user's role is changed to instructor as well as their hourly wage
    @PutMapping("/role-requests/{id}")
    public ResponseEntity<?> approveRoleRequest(@PathVariable("id") ObjectId id) {
        // Find the role request with the given id
        RoleRequest roleRequest = roleRequestRepo.findById(String.valueOf(id)).orElse(null);

        if (roleRequest == null) {
            return ResponseEntity.badRequest().body("Role request not found");
        }

        // Find the user with the inside roleRequest and update their role to instructor as well as their hourly wage
        userRepo.findById(roleRequest.getUserId()).ifPresent(user -> {
            user.setRole(1);
            user.setHourlyWage(roleRequest.getHourlyWage());
            userRepo.save(user);
        });

        // Delete the role request from the database
        roleRequestRepo.deleteById(String.valueOf(id));

        // Send notification to the user
        Notification notification = new Notification();
        notification.setUserId(roleRequest.getUserId());
        notification.setMessage("Your request to become an instructor has been approved");
        notification.setCreatedAt(new java.util.Date());
        notification.setRead(false);
        notificationRepo.save(notification);

        return ResponseEntity.ok("Role request approved");
    }

    // This class is used to parse the JSON object sent from the client
    @Getter
    public static class Json {
        String userId;
        int hourlyWage;

    }
}
