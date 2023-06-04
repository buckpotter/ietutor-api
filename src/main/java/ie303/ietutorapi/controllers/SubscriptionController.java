package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.Subscription;
import ie303.ietutorapi.repositories.SubscriptionRepository;
import ie303.ietutorapi.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepo;

    @Autowired
    private UserRepository userRepo;

    // save a subscription
    @PostMapping("/subscriptions")
    public ResponseEntity<?> saveSubscription(@RequestBody SubscriptionController.Json requestBody) {
        // find the user and set is_activated field of user to true
        userRepo.findUsersById(requestBody.userId).ifPresent(user -> {
            user.setIsActivated(true);
            userRepo.save(user);
        });

        // Save the subscription to the database
        Subscription subscription = new Subscription();
        subscription.setUserId(requestBody.userId);
        subscription.setPlanId(requestBody.planId);
        subscription.setDuration(requestBody.duration);
        subscription.setPaymentMethodId(requestBody.paymentMethodId);
        subscription.setTotal(requestBody.total);
        subscription.setStatus("success");

        // created_at is set automatically by MongoDB
        subscription.setCreatedAt(new java.util.Date());
        subscription.setStartDate(new java.util.Date());

        // set end date, which is start date + duration * 30 days
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(subscription.getStartDate());
        cal.add(java.util.Calendar.DAY_OF_MONTH, subscription.getDuration() * 30);
        subscription.setEndDate(cal.getTime());

        subscriptionRepo.save(subscription);

        return ResponseEntity.ok("successfully saved subscription");
    }

    @Getter
    @Setter
    public static class Json {
        public String userId;
        public String planId;
        public Integer duration;
        public String paymentMethodId;
        public Double total;
    }


}
