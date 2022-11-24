package com.example.demo;

import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Commanline use kiya hai.");
		List<Author> authorList=authorRepository.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(30,"India","m");
		authorList.forEach(x-> System.out.println(x.getName()));
	}
}
