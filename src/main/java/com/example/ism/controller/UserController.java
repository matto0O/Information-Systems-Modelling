package com.example.ism.controller;

import com.example.api.UserApi;
import com.example.ism.model.User;
import com.example.ism.service.UserService;
import com.example.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController implements UserApi {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        Long newId = userService.addUser(user).getId();
        userDTO.setId(newId);

        return new ResponseEntity<>(userDTO, org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.findAllUsers();
        List<UserDTO> userDTOs = new ArrayList<>();
        users.forEach(
                user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setPassword(user.getPassword());
                    userDTO.setPhoneNumber(user.getPhoneNumber());
                    userDTOs.add(userDTO);
                }
        );

        return new ResponseEntity<>(userDTOs, org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        if(userService.deleteUserById(id) == null) {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> loginUser(String email, String password) {
        return UserApi.super.loginUser(email, password);
    }

    @Override
    public ResponseEntity<Void> logoutUser() {
        return UserApi.super.logoutUser();
    }

    @Override
    public ResponseEntity<Void> updateUser(Integer id, UserDTO userDTO) {
        User user = new User();
        user.setId(Long.valueOf(id));
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        userService.updateUser(id, user);

        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }
}
