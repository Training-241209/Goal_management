package com.gm.goal_m.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gm.goal_m.dto.UserLoginRequest;
import com.gm.goal_m.model.User;
import com.gm.goal_m.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User findUserById(int id) {
        Optional<User> userContainer = userRepository.findById((long) id);
        if (userContainer.isPresent()) {
            return userContainer.get();
        } else {
            return null;
        }
    }

    public User findUSerByUsername(String str) {
        return userRepository.findByUsername(str).get();
    }

    public boolean canLogIn(UserLoginRequest userLoginRequest) {
        boolean correctCredentials = false;
        List<User> users = new ArrayList<>();
        users = getAllUsers();
        for (User myUser : users) {
            if (myUser.getUsername().equals(userLoginRequest.getEmail())) {
                if (passwordEncoder.matches(userLoginRequest.getPassword(), myUser.getPassword())) {
                    correctCredentials = true;
                } else {
                    correctCredentials = false;
                }
            }
        }
        return correctCredentials;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        if (userRepository.findAll() != null) {
            users = userRepository.findAll();
            System.out.println(users);
        }

        return users;
    }

}
