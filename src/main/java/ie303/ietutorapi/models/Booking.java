package ie303.ietutorapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookings")
public class Booking {
    @Id
    private String _id;
    private String instructor_id;
    private String student_id;
    private String subject_id;
    private String start_time;
    private String end_time;
    private String booking_status;
    private String date; // date of the booking
    private Recurrence recurrence;

    public static class Recurrence {
        private String frequency;
        private int interval;
        private String end_date;

        // ...
    }
}
