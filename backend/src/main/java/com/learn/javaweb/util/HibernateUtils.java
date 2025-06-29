package com.learn.javaweb.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.learn.javaweb.model.Admin;
import com.learn.javaweb.model.Department;
import com.learn.javaweb.model.Staff;
import com.learn.javaweb.model.Student;

public class HibernateUtils {
	private static SessionFactory sessionFactory;
	static {
	    try {
	        Configuration configuration = new Configuration().configure()
	                .addAnnotatedClass(Department.class)
	                .addAnnotatedClass(Staff.class)
	                .addAnnotatedClass(Admin.class)
	                .addAnnotatedClass(Student.class);
	
	        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                .applySettings(configuration.getProperties()).build();
	
	        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    } catch (Exception e) {
            System.err.println("Failed to create SessionFactory: " + e);
	        e.printStackTrace();
            throw new ExceptionInInitializerError(e);
	    }
	}
	
	public static SessionFactory getSessionFactory() {
	    return sessionFactory;
	}
}
