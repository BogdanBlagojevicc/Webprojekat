package com.example.FitnessCenter.service;

import com.example.FitnessCenter.model.User;

import java.util.List;

public interface UserService {

    User findOne(Long id);

    User findOneUsernameAndPassword(String username, String password);

    List<User> findAll();

    User create(User user) throws Exception;

    User update(User user) throws Exception;

    void delete(Long id);

}
