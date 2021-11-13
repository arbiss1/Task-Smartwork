package task.smartwork.arbis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.smartwork.arbis.domain.PhoneBook;
import task.smartwork.arbis.repository.PhoneBookRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhoneBookService {

    @Autowired
    PhoneBookRepository phoneBookRepository;

    public void savePhoneBook(PhoneBook phoneBook) {
        phoneBookRepository.saveAndFlush(phoneBook);
    }

    @Transactional
    public void deletePhoneBook(PhoneBook phoneBook) {
        phoneBookRepository.deleteById(phoneBook.getId());
    }

    public PhoneBook findById(long id) {
        return phoneBookRepository.findById(id);
    }
}
