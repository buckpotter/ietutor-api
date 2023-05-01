package ie303.ietutorapi.controllers;

import ie303.ietutorapi.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @Autowired
    NotificationRepository notificationRepo;

    // Lấy tất cả thông báo của user theo email
    @GetMapping("/notifications/{id}")
    public ResponseEntity<?> getAllNotifications(@PathVariable("id") String id) {
        // Get all notifications from MongoDB database
        var notifications = notificationRepo.findByUserId(id);
        return ResponseEntity.ok(notifications);
    }


    // Đánh dấu đã đọc notification
    @PutMapping("/notifications/{id}")
    public ResponseEntity<?> readNotification(@PathVariable("id") String id) {
        // Get all notifications from MongoDB database
        var notification = notificationRepo.findById(id).get();
        notification.setRead(true);
        notificationRepo.save(notification);
        return ResponseEntity.ok(notification);
    }
}
