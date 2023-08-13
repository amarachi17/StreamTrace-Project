package com.techwiz.StreamTrace.service;


import com.techwiz.StreamTrace.repository.UserRepository;
import com.techwiz.StreamTrace.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object register(User user) {
        // validate user information
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (user.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }

        // check if username or email already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalStateException("Username already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // save user to database
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        // validate username and password
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // find user by username
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        // verify password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Invalid password");
        }
        // return user
        return user;
    }
}
