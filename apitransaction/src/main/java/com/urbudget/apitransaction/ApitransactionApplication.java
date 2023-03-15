package com.urbudget.apitransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;

@SpringBootApplication(scanBasePackages = {"com.urbudget.apitransaction"})
public class ApitransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApitransactionApplication.class, args);
	}

}
