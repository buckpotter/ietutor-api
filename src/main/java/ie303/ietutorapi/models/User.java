package ie303.ietutorapi.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String _id;

    @Field("client_id")
    private String clientId;

    @Field("client_secret")
    private String tenant;


    private String email;
    private String password;
    private String connection;
    private String username;
    @Field("role")
    private int role;
    @Field("hourly_wage")
    private int hourlyWage;
    private String bio;
    private String phone;
    private String address;
}
