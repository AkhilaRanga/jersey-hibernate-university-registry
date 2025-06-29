package com.learn.javaweb.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learn.javaweb.model.Admin;
import com.learn.javaweb.util.HibernateUtils;

public class AdminDao {
    private final SessionFactory sessionFactory;

    public AdminDao() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }
    
    public void save(Admin admin) {
        try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();
	        session.persist(admin);
	        session.getTransaction().commit();
	        session.close();
        }
    }

    public List<Admin> findAll() {
        try (Session session = sessionFactory.openSession()) {
	        List<Admin> adminList = session.createQuery("FROM Admin", Admin.class).list();
	        session.close();
	        return adminList;
        }
    }

    public Admin findById(int adminId) {
        try (Session session = sessionFactory.openSession()) {
        	Admin admin = session.createQuery("FROM Admin WHERE adminId = :adminId", Admin.class)
	                .setParameter("adminId", adminId)
	                .getSingleResult();
	        session.close();
	        return admin;
        } catch (NoResultException e) {
            // Service class will handle this
            return null;
        }
    }

    public Admin findByStaffCode(String staffCode) {
        try (Session session = sessionFactory.openSession()) {
        	Admin admin = session.createQuery("FROM Admin WHERE staffCode = :staffCode", Admin.class)
	                .setParameter("staffCode", staffCode)
	                .getSingleResult();
	        session.close();
	        return admin;
        } catch (NoResultException e) {
            // Service class will handle this
            return null;
        }
    }
    
    public void update(Admin admin) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(admin);
            session.getTransaction().commit();
	        session.close();
        }
    }
    
    public void deleteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            if (admin != null) {
                session.remove(admin);
            }
            session.getTransaction().commit();
	        session.close();
        }
    }
}
