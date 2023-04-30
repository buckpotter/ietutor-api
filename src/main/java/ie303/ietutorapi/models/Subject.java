package ie303.ietutorapi.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "subjects")
public class Subject {
    @Id
    private String _id;
    private String name;
    private String description;
    private List<String> instructor_ids;
}
