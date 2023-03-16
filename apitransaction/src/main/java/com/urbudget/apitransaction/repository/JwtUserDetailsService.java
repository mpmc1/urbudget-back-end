package com.urbudget.apitransaction.repository;

import com.urbudget.apitransaction.domain.Person;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


public class JwtUserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Person person = personRepository.findById(username).orElseThrow(()->new CustomException("User not found with username"));

        return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(),
                new ArrayList<>());
    }

    public Person save(Person person) {
        person.setPassword(bcryptEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

}
