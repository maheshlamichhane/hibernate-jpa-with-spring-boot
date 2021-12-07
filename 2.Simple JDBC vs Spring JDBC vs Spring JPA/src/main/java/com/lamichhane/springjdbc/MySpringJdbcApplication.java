package com.lamichhane.springjdbc;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lamichhane.springjdbc.bean.Person;
import com.lamichhane.springjdbc.service.PersonJdbcDAO;

//@SpringBootApplication
public class MySpringJdbcApplication implements CommandLineRunner {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJdbcDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(MySpringJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		logger.info("All Users -> {}",dao.findAll());
		logger.info("User id 1001 -> {}",dao.findById(1001));
		logger.info("Is User Deleted: {}",dao.deleteById(1002));
		logger.info("Inserting 1003 -> {}",dao.insert(new Person(1003,"Daring Pits","Korea",new Date())));
		logger.info("Updating 1001 -> {}",dao.update(new Person(1001,"Hari","Munich",new Date())));
	}

}
