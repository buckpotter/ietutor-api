package ie303.ietutorapi.repositories;

import ie303.ietutorapi.models.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
}
