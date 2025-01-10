package com.gm.goal_m.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gm.goal_m.dto.UserLoginRequest;
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

    public User registerUser(UserRequestRegDTO userRequestRegDTO) {
        String hashedPassword = passwordEncoder.encode(userRequestRegDTO.getPassword());
        User user = new User(userRequestRegDTO.getEmail(), hashedPassword, 
        userRequestRegDTO.getFirstName(), userRequestRegDTO.getLastName());
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

    public User findUserByEmail(String str) {
        if(userRepository.findByEmail(str).isPresent()){
            return userRepository.findByEmail(str).get();
        } else {
            return null;
        }
    }

    public boolean canLogIn(UserLoginRequest userLoginRequest) {
        boolean correctCredentials = false;
        User myUser = new User();
        if(findUserByEmail(userLoginRequest.getEmail()) != null){
            myUser = findUserByEmail(userLoginRequest.getEmail());
            if(passwordEncoder.matches(userLoginRequest.getPassword(), myUser.getPassword())){
                correctCredentials = true;
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
