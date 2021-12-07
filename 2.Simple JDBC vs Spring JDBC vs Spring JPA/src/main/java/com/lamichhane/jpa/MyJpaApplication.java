package com.lamichhane.jpa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lamichhane.jpa.bean.Person;
import com.lamichhane.jpa.service.PersonJpaRepository;


@SpringBootApplication
public class MyJpaApplication implements CommandLineRunner
{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJpaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MyJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		logger.info("User id 1001:-> {}",repository.findById(1001));
		logger.info("Inserting with random id-> {}",repository.insert(new Person("Daring Pits","Korea",new Date())));
		logger.info("Updating 1001 -> {}",repository.update(new Person(1001,"Hari","Munich",new Date())));
		repository.deleteById(1002);
		logger.info("All Users -> {}",repository.findAll());
		/*
		logger.info("All Users -> {}",dao.findAll());
		logger.info("User id 1001 -> {}",dao.findById(1001));
		logger.info("Is User Deleted: {}",dao.deleteById(1002));
		logger.info("Inserting 1003 -> {}",dao.insert(new Person(1003,"Daring Pits","Korea",new Date())));
		logger.info("Updating 1001 -> {}",dao.update(new Person(1001,"Hari","Munich",new Date())));
		*/
	}

}
