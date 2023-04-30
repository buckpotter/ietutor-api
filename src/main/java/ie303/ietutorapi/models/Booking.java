package ie303.ietutorapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookings")
public class Booking {
    @Id
    private String _id;

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
}
