package com.urbudget.apitransactions.repositories;

import com.urbudget.apitransactions.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
