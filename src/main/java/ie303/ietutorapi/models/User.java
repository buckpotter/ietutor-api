package ie303.ietutorapi.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private String client_id;
    private String tenant;
    private String email;
    private String password;
    private String connection;
    private String username;
    private int role;
    private int hours_rate;
    private String bio;
}
