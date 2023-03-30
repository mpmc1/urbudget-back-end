package com.urbudget.apitransaction.service.jwtuserdetail;

import com.urbudget.apitransaction.domain.person.Person;
import com.urbudget.apitransaction.repository.person.PersonRepository;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class JwtUserDetailsService implements UserDetailsService{
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
