package ie303.ietutorapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")
public class Review {
    /*
    Reviews Collection:
    {
      _id: ObjectId,
      instructor_id: ObjectId, // reference to the instructor being reviewed
      student_id: ObjectId, // reference to the student who wrote the review
      rating: Number, // the rating (out of 5) given by the student
      comment: String // the comment written by the student
      created_at: Date // the date on which the review was submitted
    }
    * */
    @Id
    private String _id;


    private String instructor_id;
    private String student_id;
    private int rating;
    private String comment;
    private Date created_at;

    public void setCreated_at(Date date) {
        this.created_at = date;
    }
}
