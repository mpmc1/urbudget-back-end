package com.urbudget.apitransactions.services;

import com.urbudget.apitransactions.domain.user.User;
import com.urbudget.apitransactions.repositories.UserRepository;
import com.urbudget.apitransactions.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAll() {
        return userRepository.findAll();

    }
    public User getById(String id){

        return userRepository.findById(id).orElseThrow(()->new CustomException("User Not Found"));
    }

    public User save(User user){

        return userRepository.save(user);
    }


    public User update(User user){

        return userRepository.save(user);
    }
}
