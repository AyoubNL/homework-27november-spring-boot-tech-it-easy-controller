package nl.novi.techiteasycontroller.repositories;

import nl.novi.techiteasycontroller.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
