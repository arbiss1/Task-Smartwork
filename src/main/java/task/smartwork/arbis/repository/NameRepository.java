package task.smartwork.arbis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.smartwork.arbis.domain.Name;

public interface NameRepository extends JpaRepository<Name,Long> {
}
