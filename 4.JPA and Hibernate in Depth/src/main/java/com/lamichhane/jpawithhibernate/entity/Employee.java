package com.lamichhane.jpawithhibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
/* @Inheritance(strategy=InheritanceType.SINGLE_TABLE)  /* this is not good beacouse of data integrity i.e nullable column */
/* @DiscriminatorColumn(name="EmployeeType") */
/* @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) this is also not good beacouse both table have duplicate column */
@Inheritance(strategy=InheritanceType.JOINED)
/* we can also use MappedSuperClass annotation and also remove entity annotation from table,data saved in different table */
public class Employee
{
	@Id
	@GeneratedValue
	private Long id;
	
	
	@Column(name="name",nullable=false)
	private String name;


	public Employee() {
	}


	public Employee(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	
    
	
	
	
	
	
	

}
