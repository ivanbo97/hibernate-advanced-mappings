package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;




public class CreateInstructorBiDirect {

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
					
					// create the objects 
					
					Instructor tempInstructor = new Instructor("Susan","Wall","susanwall@luv2code.com");
					
					InstructorDetail tempInstructorDetail = 
							new InstructorDetail(
									"http://youtube.com",
									"Game!!!");
					
					// associate the objects
					tempInstructor.setInstructorDetail(tempInstructorDetail);
					// start transaction
					session.beginTransaction();
					
					// save the instructor
					//NOTE: This will also save tempInstructorDetail object beacuse of CascadeType.ALL
					System.out.println("Saving instructor: " + tempInstructor);
					session.save(tempInstructor);
					
					// commit transaction
					session.getTransaction().commit();
					System.out.println("Done!");
					
				} finally {
					factory.close();
					session.close();
				}
				

	}

}
