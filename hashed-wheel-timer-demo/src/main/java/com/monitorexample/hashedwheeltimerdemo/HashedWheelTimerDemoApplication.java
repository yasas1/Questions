package com.monitorexample.hashedwheeltimerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class HashedWheelTimerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HashedWheelTimerDemoApplication.class, args);
	}

}
