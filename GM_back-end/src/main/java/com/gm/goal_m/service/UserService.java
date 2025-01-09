package com.gm.goal_m.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gm.goal_m.dto.UserRequestRegDTO;
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

    public User registerUser(UserRequestRegDTO userRequestRegDTO){
        User user = new User();
        String hashedPassword = passwordEncoder.encode(userRequestRegDTO.getPassword());
        user.setUsername(userRequestRegDTO.getUsername());
        user.setPassword(hashedPassword);
        user.setFirstName(userRequestRegDTO.getFirstName());
        user.setLastName(userRequestRegDTO.getLastName());
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
        return userRepository.findByUsername(str).get();
    }
    
}
