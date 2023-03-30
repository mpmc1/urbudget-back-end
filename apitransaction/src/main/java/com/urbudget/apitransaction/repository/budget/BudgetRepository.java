package com.urbudget.apitransaction.repository.budget;

import com.urbudget.apitransaction.domain.budget.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends CrudRepository<Budget, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM budget b WHERE b.user_email = ?1")
    public Iterable<Budget> getAllByPerson(String person);
    @Query(nativeQuery = true,
            value = "SELECT * FROM budget b WHERE b.user_email = ?1 AND b.year = ?2")
    public Optional<Budget> getOneByPersonAndYear(String personEmail, int year);
    @Query(nativeQuery = true,
            value = "SELECT * FROM budget b WHERE b.id_budget = ?2 AND b.user_email= ?1")
    public Optional<Budget> getOneByPersonAndId(String personEmail, String idBudget);
}

