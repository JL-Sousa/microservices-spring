package com.tecsoftblue.userservice.service.impl;

import com.tecsoftblue.userservice.entities.User;
import com.tecsoftblue.userservice.exception.ResourceNotFoundException;
import com.tecsoftblue.userservice.repository.UserRepository;
import com.tecsoftblue.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Usuário não encontrado com ID: " + userId));
    }
}
