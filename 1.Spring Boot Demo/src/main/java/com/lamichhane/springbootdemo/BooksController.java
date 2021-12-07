package com.lamichhane.springbootdemo;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController
{
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return Arrays.asList(new Book(1,"Mastering Spring 5.2","Mahesh Lamichhane"),new Book(2,"Mastering Hibernate","Mukesh Lamichhane"));		
	}
}
