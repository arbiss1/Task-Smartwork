package task.smartwork.arbis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.smartwork.arbis.domain.User;
import task.smartwork.arbis.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public boolean isUsernamePresent(User user) {
        return listAll().stream().anyMatch(username -> username.getUsername().equals(user.getUsername()));
    }

    public boolean isUserValid(User user){
        return listAll().stream().anyMatch(p -> p.getPassword().equals(user.getPassword())
                && p.getUsername().equals(user.getUsername()));
    }
}
