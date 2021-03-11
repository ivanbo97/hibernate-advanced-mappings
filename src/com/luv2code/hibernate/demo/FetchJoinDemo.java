package com.luv2code.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

import antlr.collections.List;


public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
				SessionFactory factory = new Configuration()
										 .configure("hibernate.cfg.xml")
										 .addAnnotatedClass(Instructor.class)
										 .addAnnotatedClass(InstructorDetail.class)
										 .addAnnotatedClass(Course.class)
										 .buildSessionFactory();
				
				Session session = factory.getCurrentSession();
				
				try {
					
					
					// start transaction
					session.beginTransaction();
					
					// get instructor from db using HQL. When the query
					// is executed the instructor and all its courses will be 
					// loaded at once.
					int instructorId= 1;
					Query<Instructor> query =
							session.createQuery("select i from Instructor i "
											+ "JOIN FETCH i.courses " 
											+ "where i.id=:theInstructorId"	,									
									Instructor.class);
					
					// set parameter
					query.setParameter("theInstructorId", instructorId);
					
					// execute query and get instructor
					Instructor tempInstructor = query.getSingleResult();
					
					// retrieve the courses for this instructor
					System.out.println("luv2code: Instructor: " + tempInstructor);
					
					// commit transaction
					session.getTransaction().commit();
					System.out.println("luv2code: Done!");
					session.close();
					
					// get all the courses for the instructor
					System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
					
				} finally {
					factory.close();
					session.close();
				}
				

	}

}
