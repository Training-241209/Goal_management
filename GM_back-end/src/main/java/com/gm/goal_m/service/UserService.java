package com.gm.goal_m.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.gm.goal_m.model.User;
import com.gm.goal_m.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User findUserById(int id){
        Optional<User> userContainer = userRepository.findById((long) id);
        if(userContainer.isPresent()){
            return userContainer.get();
        } else {
            return null;
        }
    }

    public User findUSerByUsername(String str){
        return userRepository.findUserByUsername(str).get();
    }
    
}
