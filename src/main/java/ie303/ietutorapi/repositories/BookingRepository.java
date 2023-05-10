package ie303.ietutorapi.repositories;

import ie303.ietutorapi.models.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    // find bookings by user id
    List<Booking> findByInstructorId(String userId);
}
