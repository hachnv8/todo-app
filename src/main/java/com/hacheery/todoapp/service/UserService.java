package com.hacheery.todoapp.service;

import com.hacheery.todoapp.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void updateProfile(User updatedUser);

    void changePassword(String oldPassword, String newPassword);

    boolean hasRole(String role);

    Set<String> getRoles();

    void savePreference(String key, String value);

    String getPreference(String key);
    User createUser(User user);

    List<User> getUsers();

    User getUserById(Long userId);

    User updateUser(Long userId, User user);
}
