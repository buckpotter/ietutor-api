package ie303.ietutorapi.repositories;

import ie303.ietutorapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // find a user by id and update the role to 1
//    User findByIdAndRole(String id, int role);
}
