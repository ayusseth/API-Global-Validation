package main.service;

import lombok.extern.slf4j.Slf4j;
import main.entity.User;
import main.exception.UserNotFoundException;
import main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUser(int id) {
        return userRepo.findById(id);
    }

    @Override
    public User updateUser(int id, User user) {
        User updateUser = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id + " not found for update"));
        if (user.getName() != null) {
            updateUser.setName(user.getName());
        }
        if (user.getAge() != 0) {
            updateUser.setAge(user.getAge());
        }
        if (user.getMail() != null) {
            updateUser.setMail(user.getMail());
        }
        return userRepo.save(updateUser);
    }

    @Override
    public void deleteUser(int id) {

        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            throw new UserNotFoundException(id + " is not found for delete");
        }
    }
}
