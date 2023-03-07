package com.urbudget.apitransactions.services;

import com.urbudget.apitransactions.domain.Transaction.Transaction;
import com.urbudget.apitransactions.domain.User.User;
import com.urbudget.apitransactions.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {

    @Autowired
    UserRepository userRepository;

    private Iterable<User> getAll() {
        return userRepository.findAll();

    }
    private Optional<User> getById(String id){

        return userRepository.findById(id);
    }

    private User create(User user){

        return userRepository.save(user);
    }


    private User update(User user){

        return userRepository.save(user);
    }
}
