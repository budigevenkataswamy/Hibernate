package com.venkat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.venkat.hibernate.demo.entity.Course;
import com.venkat.hibernate.demo.entity.Instructor;
import com.venkat.hibernate.demo.entity.InstructorDetail;
import com.venkat.hibernate.demo.entity.Review;
import com.venkat.hibernate.demo.entity.Student;

public class CreateCoursesAndStudentDemo {

	public static void main(String[] args) {
		
		//create a Session factory
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create a session
		Session session= factory.getCurrentSession();
		
		 try {
			 session.beginTransaction();
			 
			Course tempCourse = new Course("Pacmen -How To Score One Million Points");

			System.out.println("Saving the course....");
			session.save(tempCourse);
			System.out.println("Saved the course...."+tempCourse);
			
			Student tempStudent1=new Student("venkata","swamy","venkat@gmail.com");
			Student tempStudent2=new Student("mery","public","mery@gmail.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			System.out.println("\nsaving the students.......");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("saved students...."+tempCourse.getStudents());
			
			
			
			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");
			 
			
		} finally {
			session.close();
			factory.close();
			
		}
	}

}
