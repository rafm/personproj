package com.daitangroup.initproj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.daitangroup.initproj.model.Person;
import com.daitangroup.initproj.repository.PersonRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired private PersonRepository personRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		personRepository.save(new Person("Joaquim", "BR"));
		personRepository.save(new Person("Bran", "US"));
		personRepository.save(new Person("Yushin", "JP"));
		personRepository.save(new Person("Pablo", "ES"));
	}
}
