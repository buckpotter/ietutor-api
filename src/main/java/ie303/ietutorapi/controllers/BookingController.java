package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.Booking;
import ie303.ietutorapi.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {
    @Autowired
    private BookingRepository bookingRepo;

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        // Get all bookings from MongoDB database
        List<Booking> bookings = bookingRepo.findAll();

        if (bookings.size() == 0) {
            // return json object with error message
            return ResponseEntity.badRequest().body("No bookings found");
        }

        return ResponseEntity.ok(bookings);
    }
}