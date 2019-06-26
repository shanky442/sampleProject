package shl.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import shl.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
