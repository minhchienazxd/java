package com.example.service;
import com.example.entity.User;
import com.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("không yifm thấy người dùng với ID: " + id));
    }

    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("email đã được đăng kí");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Transactional
    public User updateUser(Long id, User updateUser) {
        User existing = getUserById(id);
        existing.setName(updateUser.getName());
        existing.setEmail(updateUser.getEmail());
        if (!updateUser.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        return userRepository.save(existing);

    }
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("người dùng không tồn tại");

        }
        userRepository.deleteById(id);
    }
}
