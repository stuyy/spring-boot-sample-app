package com.ansonfoong.service;

import com.ansonfoong.models.User;
import com.ansonfoong.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void update(User oldUser, User newUser) {

    }
    @Override
    public User addUser(User user) {
        this.encrypt(user);
        return this.userRepository.save(user);
    }
    @Override
    public void updateUsername(Long id, String username) {
        try {
            Optional<User> findUser = this.userRepository.findById(id);
            if(findUser.isPresent()) {
                findUser.get().setUsername(username);
                this.userRepository.save(findUser.get());
                System.out.println("Updated username.");
            }
        }
        catch(Exception error) {
            System.out.println(error.toString());
        }
    }
    @Override
    public void delete(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        try {
            if(user.isPresent()) {
                this.userRepository.deleteById(user.get().getId());
                System.out.println("Deleted.");
            }
        }
        catch(Exception error) {
            System.out.println(error);
        }
    }
    @Override
    public boolean compare(User user, String password) {
        return this.encoder.matches(password, user.getPassword());
    }
    @Override
    public void encrypt(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        System.out.println("Encrypted Password.");
    }
    
    public Optional<User> getUser(Long id) {
        return this.userRepository.findById(id);
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
}
