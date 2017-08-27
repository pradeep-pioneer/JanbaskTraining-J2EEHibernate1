package com.janbask.training3.main;

import com.janbask.training3.misc.HibernateUtil;
import com.janbask.training3.model.EmployeeJpa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class HibernateJpaEx {
    public static void main(String[] args) {
        EmployeeJpa emp = new EmployeeJpa();
        emp.setName("James");
        emp.setRole("Project Manager");
        emp.setInsertTime(new Date());

        EntityManagerFactory emf = HibernateUtil.getEntityManager();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(emp);
        transaction.commit();
        System.out.println("Employee ID="+emp.getId());
        em.close();
        emf.close();
    }
}
