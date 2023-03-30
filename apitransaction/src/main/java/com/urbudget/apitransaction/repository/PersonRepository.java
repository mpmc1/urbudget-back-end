package com.urbudget.apitransaction.repository;

import com.urbudget.apitransaction.domain.person.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
}
