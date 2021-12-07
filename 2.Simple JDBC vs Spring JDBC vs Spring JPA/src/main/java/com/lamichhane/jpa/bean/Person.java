package com.lamichhane.jpa.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="person")
@NamedQuery(name="find_all_persons",query="select p from Person p")
public class Person 
{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	
	public Person() 
	{
		
	}
	public Person(int id, String name, String location, Date birthDate)
	{
		this.id = id;
		this.name = name;
		this.location = location;
	
	}
	
	public Person(String name, String location, Date birthDate)
	{
		this.name = name;
		this.location = location;
	}
	
	
	
	@Override
	public String toString() 
	{
		return "Person [id=" + id + ", name=" + name + ", location=" + location+"]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
	
	
}
