package com.lamichhane.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMathTest {
	
	 MyMath mymath=new MyMath();
	 
	  @Before
	   public void before() 
	   {
		   System.out.println("Before");
	   }
	  
	  @After
	  public void after() {
		  System.out.println("After");
	  }
	  
	  @BeforeClass
	  public static void beforeClass()
	  {
		  System.out.println("Before Class");
	  }
	  
	  @AfterClass
	  public static void afterClass() {
		  System.out.println("After Class");
	  }
	  
		@Test
		public void sum_with3numbers()
		{
			System.out.println("Test1");
		   int sum = mymath.sum(new int[] {1,2,3});
		   assertEquals(6, sum);
		   
		}
	
	@Test
	public void sum_with1numbers()
	{
		System.out.println("Test2");
	   
	   int anothersum = mymath.sum(new int[] {3});
	   assertEquals(3, anothersum);
	   
	}

}
