package task.smartwork.arbis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.smartwork.arbis.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername (String username);
}
