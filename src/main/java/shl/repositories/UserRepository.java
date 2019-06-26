package shl.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import shl.models.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}
