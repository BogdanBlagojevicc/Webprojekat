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
    public User delete(User user) throws Exception{
        User userToDelete = this.userRepository.getOne(user.getId());
        if(userToDelete == null){
            throw new Exception("User doesn't exist");
        }

        userToDelete.setIsDeleted(true);

        User savedUser  = this.userRepository.save(userToDelete);
        return savedUser;
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
    public User findOneUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    //napisao
    @Override
    public User update(User user) throws Exception {
        User userToUpdate = this.userRepository.getOne(user.getId());
        if (userToUpdate == null) {
            throw new Exception("User doesn't exist!");
        }

        userToUpdate.setActive(true);

        User savedUser = this.userRepository.save(userToUpdate);
        return savedUser;
    }

    @Override
    public User change(User user) throws Exception{
        User userToChange = this.userRepository.getOne(user.getId());
        if(userToChange == null){
            throw new Exception("User doesn't exist!");
        }

        if(!user.getFirstName().equals("")){
            userToChange.setFirstName(user.getFirstName());
        }

        if(!user.getLastName().equals("")){
            userToChange.setLastName(user.getLastName());
        }

        if(!user.getEmail().equals("")){
            userToChange.setEmail(user.getEmail());
        }

        if(!user.getPassword().equals("")){
            userToChange.setPassword(user.getPassword());
        }

        if(!user.getPhoneNumber().equals("")){
            userToChange.setPhoneNumber(user.getPhoneNumber());
        }

        User savedUser = this.userRepository.save(userToChange);
        return savedUser;
    }


}
