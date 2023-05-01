package ie303.ietutorapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "instructor_subject_requests")
public class InstructorSubjectRequest {
    /*
    InstructorSubjectRequests Collection:
    {
      _id: ObjectId,
      instructor_id: ObjectId, // reference to the instructor submitting the request
      subject_id: ObjectId, // reference to the subject the instructor wishes to teach
      message: String, // an optional message from the instructor to the admin
      is_approved: Boolean, // whether the request has been approved by the admin
      created_at: Date // the date on which the request was submitted
    }
     */

    @Id
    private String id;

    @Field("instructor_id")
    private String instructorId;

    @Field("subject_id")
    private String subjectId;

    @Field("message")
    private String message;

    @Field("is_approved")
    private boolean isApproved;

    @Field("created_at")
    private Date createdAt;
}
