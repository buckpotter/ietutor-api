package ie303.ietutorapi.repositories;

import ie303.ietutorapi.models.InstructorSubjectRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorSubjectRequestRepository extends MongoRepository<InstructorSubjectRequest, String> {
}
