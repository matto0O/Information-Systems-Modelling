package com.example.ism.serviceimpl;

import com.example.ism.model.User;
import com.example.ism.repository.UserRepository;
import com.example.ism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User deleteUserById(long id) {

        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            userRepository.deleteById(id);
            return u.get();
        }

        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(long id, User user) {
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            User existingUser = u.get();
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            return userRepository.save(existingUser);
        }
        user.setId(id);
        return user;
    }

    @Override
    public User findUserById(long id) {
        List<User> a = userRepository.findAll();
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean loginUser(String email, String password) {
        return false;
    }

    @Override
    public boolean logoutUser() {
        return false;
    }
}
