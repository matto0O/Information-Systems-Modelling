package com.example.ism.service;

import com.example.ism.model.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public User deleteUserById(long id);
    public List<User> findAllUsers();
    public User updateUser(long id, User user);
    //@Cacheable ("users")
    public User findUserById(long id);

    public boolean loginUser(String email, String password);
    public boolean logoutUser();
}
