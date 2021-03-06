package com.janbask.training3.misc;


import java.util.Properties;

import com.janbask.training3.model.Employee;
import com.janbask.training3.model.EmployeeJpa;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    //XML based configuration
    private static SessionFactory sessionFactory;

    //Annotation based configuration
    private static SessionFactory sessionAnnotationFactory;

    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.addClass(Employee.class);
            configuration.addResource("com/janbask/training3/model/employee.hbm.xml");
            configuration.addResource("com/janbask/training3/model/executiveEmployee.hbm.xml");
            configuration.addResource("com/janbask/training3/model/ehcache.xml");
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("PERSISTENCE");
    }

    public static EntityManagerFactory getEntityManagerFactory1(){
        return Persistence.createEntityManagerFactory("PERSISTENCE1");
    }

}