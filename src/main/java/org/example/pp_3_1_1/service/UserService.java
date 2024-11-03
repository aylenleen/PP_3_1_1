package org.example.pp_3_1_1.service;

import org.example.pp_3_1_1.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void add(User user);
    void update(User user);
    void delete(User user);
    User getUserById(int id);

}
