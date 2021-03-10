package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;




public class DeleteDemo {

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
					
					// get instructor by primary key / id
					int insturctorId = 2;
					
					Instructor instructor = session.get(Instructor.class,insturctorId);
					System.out.println("Retrived instructor : " + instructor);
					
					//delete instructor
					// NOTE: The corresponding instructor detail record will be also deleted
					// because of CascadeType.All
					if(instructor != null) {
						System.out.println("Deleting instructor: " + instructor);
						session.delete(instructor);
					}

					// commit transaction
					session.getTransaction().commit();
					System.out.println("Done!");
					
				} finally {
					session.close();
				}
	}

}
