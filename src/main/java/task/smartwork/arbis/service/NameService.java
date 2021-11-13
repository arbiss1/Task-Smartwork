package task.smartwork.arbis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import task.smartwork.arbis.domain.Name;
import task.smartwork.arbis.domain.PhoneBook;
import task.smartwork.arbis.repository.NameRepository;

@Service
public class NameService {

    @Autowired
    NameRepository nameRepository;

    public void saveName(PhoneBook phoneBook) {
        Name name = new Name();
        name.setFirstName(phoneBook.getName().getFirstName());
        name.setLastName(phoneBook.getName().getLastName());
        nameRepository.saveAndFlush(name);
    }
}
