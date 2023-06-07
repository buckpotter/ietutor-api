package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.Booking;
import ie303.ietutorapi.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookingController {
    @Autowired
    private BookingRepository bookingRepo;

    // Hàm kiểm tra số ngày cách nhau giữa 2 java.util.Date
    //check number of days between 2 dates
    public static long daysBetween(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(difference);
    }


    // Get all bookings
    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        // Get all bookings from MongoDB database
        List<Booking> bookings = bookingRepo.findAll();

        if (bookings.isEmpty()) {
            // return json object with error message
            return ResponseEntity.badRequest().body("No bookings found");
        }
         //return json object with bookings
        return ResponseEntity.ok(bookings);
    }


    /*
    @Id
    private String id;

    @Field("instructor_id")
    private String instructorId;

    @Field("student_id")
    private String studentDd;

    @Field("subject_id")
    private String subjectId;

    @Field("startTime")
    private String startTime;

    @Field("endTime")
    private String endTime;

    @Field("booking_status")
    private String bookingStatus;

    @Field("Created_at")
    private Date createdAt; // date of the booking
    private Recurrence recurrence;

    public static class Recurrence {

        @Field("frequency")
        private String frequency;
        @Field("interval")
        private int interval;
        @Field("end_date")
        private String endDate;

        // ...
    }
    * */

    // add new booking

    // Get all bookings for a specific user
    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable("id") String id) {
        // Get all bookings from MongoDB database that belong to the user with the given id
        List<Booking> bookings = bookingRepo.findByInstructorId(id);


        if (bookings.isEmpty()) {
            // return json object with error message
            return ResponseEntity.badRequest().body("No bookings found");
        }

        return ResponseEntity.ok(bookings);
    }

    public boolean isConflict(Booking b1, Booking b2) {
        // Nếu cả b1.recurrence.startDate và b1.recurrence.endDate nhỏ hơn b2.recurrence.startDate lẫn b2.recurrence.endDate
        // Nếu cả b1.recurrence.startDate và b1.recurrence.endDate lớn hơn b2.recurrence.startDate lẫn b2.recurrence.endDate
        if (b1.getRecurrence().getStartDate().compareTo(b2.getRecurrence().getStartDate()) < 0 &&
                b1.getRecurrence().getEndDate().compareTo(b2.getRecurrence().getStartDate()) < 0 ||
                b1.getRecurrence().getStartDate().compareTo(b2.getRecurrence().getEndDate()) > 0 &&
                        b1.getRecurrence().getEndDate().compareTo(b2.getRecurrence().getEndDate()) > 0)
            return false;

        switch (b1.getRecurrence().getFrequency()) {
            case "daily" -> {
                return true;
            }
            case "weekly" -> {
                // Nếu số ngày cách nhau giữa 2 recurrence.startDate là bội số của 7
                if (daysBetween(b1.getRecurrence().getStartDate(), b2.getRecurrence().getStartDate()) % 7 == 0)
                    return true;
            }
        }

        return false;
    }

    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        // Kiểm tra xem có bị conflict thời gian với các booking đã có của instructor không
        List<Booking> bookings = bookingRepo.findByInstructorId(booking.getInstructorId());
        for (var b : bookings)
            // Nếu booking mới có khoảng recurrence.startDate và recurrence.endDate nằm trong khoảng của booking cũ
            if (isConflict(booking, b))
                return ResponseEntity.badRequest().body("Booking time conflict");

        // Lưu booking mới vào database
        bookingRepo.save(booking);

        return ResponseEntity.ok(booking);
    }
}