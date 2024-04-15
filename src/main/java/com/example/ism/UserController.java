package com.example.ism;

import com.example.api.UserApi;
import com.example.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<User> createUser(User user) {
        return UserApi.super.createUser(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        return UserApi.super.deleteUser(id);
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
    public ResponseEntity<Void> updateUser(String id, User user) {
        return UserApi.super.updateUser(id, user);
    }
}
