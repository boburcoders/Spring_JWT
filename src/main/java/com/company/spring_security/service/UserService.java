package com.company.spring_security.service;

import com.company.spring_security.model.Users;
import com.company.spring_security.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users saveUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Users findById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(int id) {
        this.userRepository.deleteById(id);
    }
}
