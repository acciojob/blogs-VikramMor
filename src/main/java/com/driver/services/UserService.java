package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    public void createUser(User user){
        userRepository3.save(user);
    }

    public void deleteUser(int userId){

        if(userRepository3.findById(userId).isPresent()){
            userRepository3.deleteById(userId);
        }
    }

    public void updateUser(User user){

        User olduser = findUserByUsername(user.getUsername());

        if(olduser!=null){
            olduser.setUsername(user.getUsername());
            olduser.setFirstName(user.getFirstName());
            olduser.setLastName(user.getLastName());
            olduser.setPassword(user.getPassword());
            userRepository3.save(olduser);
        }
        userRepository3.save(user);
    }

    public User findUserByUsername(String username){
        return userRepository3.findByUsername(username);
    }
}