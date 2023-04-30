package ie303.ietutorapi.repositories;

import ie303.ietutorapi.models.RoleRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRequestRepository extends MongoRepository<RoleRequest, String> {
//    List<RoleRequest> findByUserId(ObjectId userId);
}
