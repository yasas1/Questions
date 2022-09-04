package com.learn.streamdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class StreamDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamDemoApplication.class, args);
	}

}
