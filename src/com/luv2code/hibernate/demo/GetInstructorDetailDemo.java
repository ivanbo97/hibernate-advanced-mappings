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
					if(instructorDetail != null) {
						Instructor instructor = instructorDetail.getInstructor();
						System.out.println("Associated instructor: " + instructor);
					} else {
						System.out.println("Instructor detail with id=" + instrDetailId + " does not exist.");
					}
					// commit transaction
					session.getTransaction().commit();
					System.out.println("Done!");
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					factory.close();
					session.close();
				}
	}

}
