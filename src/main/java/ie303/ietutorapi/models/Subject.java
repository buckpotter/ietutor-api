package ie303.ietutorapi.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "subjects")
public class Subject {
    @Id
    private String id;
    private String name;
    private String description;

    @Field("instructor_ids")
    private List<String> instructorIds;
    
}
