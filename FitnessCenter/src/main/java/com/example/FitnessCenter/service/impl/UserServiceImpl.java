package com.example.FitnessCenter.service.impl;

import com.example.FitnessCenter.model.User;
import com.example.FitnessCenter.repository.UserRepository;
import com.example.FitnessCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //napisao
    @Override
    public User create(User user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("id must be null");
        }
        User newUser = this.userRepository.save(user);
        return newUser;
    }

    //napisao
    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    //napisao
    @Override
    public List<User> findAll() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    //napisao
    @Override
    public User findOne(Long id) {
        User user = this.userRepository.getOne(id);
        return user;
    }

    //napisao
    public User findOneUsernameAndPassword(String username, String password){
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        return user;
    }


    @Override
    public User update(User user) throws Exception {
        return null;
    }


}
