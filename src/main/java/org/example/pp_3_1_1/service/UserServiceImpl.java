package org.example.pp_3_1_1.service;

import org.example.pp_3_1_1.dao.UserRepository;
import org.example.pp_3_1_1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getReferenceById((long) id);
    }
}
