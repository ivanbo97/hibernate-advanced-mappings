package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory
				SessionFactory factory = new Configuration()
										 .configure("hibernate.cfg.xml")
										 .addAnnotatedClass(Instructor.class)
										 .addAnnotatedClass(InstructorDetail.class)
										 .buildSessionFactory();
				
				Session session = factory.getCurrentSession();
				
				try {
				
					// start transaction
					session.beginTransaction();
					
					// get the instructor detail object
					int instrDetailId = 1;
					InstructorDetail instructorDetail = session.get(InstructorDetail.class, instrDetailId);
					
					// print the instructor detail object
					System.out.println("Retrieved row: " + instructorDetail);
					
					// print the associated instructor
					Instructor instructor = instructorDetail.getInstructor();
					System.out.println("Associated instructor: " + instructor);
					
					// commit transaction
					session.getTransaction().commit();
					System.out.println("Done!");
					
				} finally {
					session.close();
				}
	}

}
