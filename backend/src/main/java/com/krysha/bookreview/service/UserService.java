package com.krysha.bookreview.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.krysha.bookreview.model.User;
import com.krysha.bookreview.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(final Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userRepository.save(user);
        }
        throw new RuntimeException("Utilisateur non trouvé");
    }

    public Optional<User> patchUser(final Long id, User user) {
        return userRepository.findById(id).map(existing -> {
            if (user.getEmail() != null) existing.setEmail(user.getEmail());
            if (user.getUsername() != null) existing.setUsername(user.getUsername());
            if (user.getName() != null) existing.setName(user.getName());
            if (user.getFirstname() != null) existing.setFirstname(user.getFirstname());
            if (user.getBirthDate() != null) existing.setBirthDate(user.getBirthDate());
            if (user.getPassword() != null) {
                existing.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userRepository.save(existing);
        });
    }
}
