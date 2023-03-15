package com.urbudget.apitransaction.service;

import com.urbudget.apitransaction.domain.Person;
import com.urbudget.apitransaction.repository.PersonRepository;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Iterable<Person> getAll() {
        return personRepository.findAll();

    }
    public Person getById(String id){

        return personRepository.findById(id).orElseThrow(()->new CustomException("User Not Found"));
    }

    public Person save(Person person){

        return personRepository.save(person);
    }


    public Person update(Person person){

        return personRepository.save(person);
    }
}
