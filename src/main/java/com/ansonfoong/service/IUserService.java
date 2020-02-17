package com.ansonfoong.service;

import com.ansonfoong.models.User;

public interface IUserService {
    void update(User oldUser, User newUser);
    void updateUsername(Long id, String username);
    void delete(Long id);
    void encrypt(User user);
    User addUser(User user);
    boolean compare(User user, String password);
}
