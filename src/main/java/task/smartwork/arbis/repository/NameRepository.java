package task.smartwork.arbis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.smartwork.arbis.domain.Name;

import java.util.List;

public interface NameRepository extends JpaRepository<Name,Long> {

    List<Name> findByFirstName(String firstName);
}
