package com.gm.goal_m.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gm.goal_m.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
}
