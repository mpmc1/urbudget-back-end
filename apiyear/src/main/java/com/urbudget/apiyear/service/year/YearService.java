package com.urbudget.apiyear.service.year;

import com.urbudget.apiyear.domain.budget.Budget;
import com.urbudget.apiyear.domain.year.Year;
import com.urbudget.apiyear.repository.year.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class YearService {

    @Autowired
    YearRepository yearRepository;

    public Flux<Year> get() {
        return yearRepository.findAll();
    }

    /*
    private Budget update(Budget budget)
    {
        return budgetRepository.;
    }*/

    public Mono<Year> save(Year year) {
        return yearRepository.save(year);
    }

    public Mono<Void> delete(String idYear) {
        return yearRepository.findById(idYear).flatMap(existingBudget -> yearRepository.deleteById(idYear));
    }

    public Mono<Year> getById(String idYear){
        return yearRepository.findById(idYear);
    }
}