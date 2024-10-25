package com.example.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.example.ecom.dto.UserUpdateRequest;
import com.example.ecom.model.User;
import com.example.ecom.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Add User
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
        // insert into users(name, email, password) values (?, ?, ?)
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
        // select * from users
    }

    // Get User By Id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
        // select * from users where id = ?
    }

    // Update User
    public User updateUser(User user, Long id) {
        User exsistUser = userRepository.findById(id).orElse(null);
        exsistUser.setName(user.getName());
        exsistUser.setEmail(user.getEmail());
        exsistUser.setPassword(user.getPassword());
        return userRepository.save(exsistUser);
        // update users set name = ?, email = ?, password = ? where id = ?
    }

    // Update User
    // dto - data transfer object
    // public User updateUser(UserUpdateRequest user, Long id) {
    // User exsistUser = userRepository.findById(id).orElse(null);
    // // exsistUser.setName(user.getName());
    // exsistUser.setEmail(user.getEmail());
    // exsistUser.setPassword(user.getPassword());
    // return userRepository.save(exsistUser);
    // // update users set name = ?, email = ?, password = ? where id = ?
    // }

    // delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        // delete from users where id = ?
    }

    // find user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
        // select * from users where email = ?
    }

}
