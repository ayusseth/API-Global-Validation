package main.service;

import lombok.extern.slf4j.Slf4j;
import main.entity.User;
import main.exception.UserNotFoundException;
import main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) {
        log.info("Creating new user: {}",user);
        User newUser=userRepo.save(user);
        log.info("User created successfully with ID={}",newUser.getId());
        return newUser;
    }

    @Override
    public List<User> getAllUser() {
        log.info("Fetching all User from Data Base...");
        List<User> list=userRepo.findAll();
        log.info("Total user found={}",list.size());
        return list;
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
