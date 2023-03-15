package com.urbudget.apitransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.urbudget.apitransactions"})
@EnableJpaRepositories(basePackages = {"com.urbudget.apitransactions"})
@EntityScan(basePackages = {"com.urbudget.apitransactions"})
public class ApitransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApitransactionsApplication.class, args);
	}

}
