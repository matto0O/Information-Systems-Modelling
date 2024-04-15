package com.example.ism;

import com.example.api.UserApi;
import com.example.model.User;
import com.example.model.Volunteer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements UserApi {

    private final List<User> users = new ArrayList<>();

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.of(Optional.of(users));
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        users.add(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        Optional<User> result = users.stream().findFirst().filter(
                user -> Math.toIntExact(user.getId()) == id
        );

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User toDelete = result.get();
        users.remove(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
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
    public ResponseEntity<Void> updateUser(Integer id, User user) {

        Optional<User> suspect = users.stream().findFirst().filter(user1 -> Math.toIntExact(user1.getId()) == id);

        if (suspect.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User userToUpdate = suspect.get();

        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
