package com.hacheery.todoapp.service.impl;

import com.hacheery.todoapp.entity.User;
import com.hacheery.todoapp.repository.UserRepository;
import com.hacheery.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void updateProfile(User updatedUser) {
        User currentUser = getCurrentUser();
        updatedUser.setId(currentUser.getId());
        userRepository.save(updatedUser);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        User currentUser = getCurrentUser();

        // Check if old password matches
        if (!passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            throw new IllegalArgumentException("Old password does not match");
        }

        // Update the password
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(currentUser);
    }

    @Override
    public boolean hasRole(String role) {
        User currentUser = getCurrentUser();
        return currentUser.getRoles().stream().anyMatch(r -> r.name().equals(role));
    }

    @Override
    public Set<String> getRoles() {
        User currentUser = getCurrentUser();
        return currentUser.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
    }

    @Override
    public void savePreference(String key, String value) {

    }

    @Override
    public String getPreference(String key) {
        User currentUser = getCurrentUser();
        // Get preference logic here
        return null;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
//            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setUsername(user.getUsername());
            return userRepository.save(existingUser);
        }
        return null;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new UsernameNotFoundException("No user is currently logged in");
        }

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
