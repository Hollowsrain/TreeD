package com.TreeD.application.service;

import com.TreeD.application.model.entity.User;

public interface UserService {

    User getUserById(Long id);

    User createUser(User user);

    User updateUserById(Long id, User user);

    void deleteUserById(Long id);
}
