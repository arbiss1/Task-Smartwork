package task.smartwork.arbis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.smartwork.arbis.domain.Name;
import task.smartwork.arbis.domain.PhoneBook;

public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {

    PhoneBook findById(long id);
}
