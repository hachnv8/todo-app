package com.hacheery.todoapp.service;

import com.hacheery.todoapp.entity.User;

import java.util.Set;

public interface UserService {
    boolean login(String username, String password);
    void logout();

//    UserDetails getUserDetails();
    void updateProfile(User updatedUser);
    void changePassword(String oldPassword, String newPassword);

    boolean hasRole(String role);
    Set<String> getRoles();

    void savePreference(String key, String value);
    String getPreference(String key);
}
