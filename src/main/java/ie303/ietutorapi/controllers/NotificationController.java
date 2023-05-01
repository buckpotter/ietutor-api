package ie303.ietutorapi.controllers;

import ie303.ietutorapi.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @Autowired
    NotificationRepository notificationRepo;
}
