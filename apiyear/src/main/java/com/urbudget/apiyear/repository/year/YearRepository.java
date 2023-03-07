package com.urbudget.apiyear.repository.year;

import com.urbudget.apiyear.domain.year.Year;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface YearRepository extends ReactiveCrudRepository<Year,String> {
}
