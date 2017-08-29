package com.janbask.training3.main;

import com.janbask.training3.misc.HibernateUtil;
import com.janbask.training3.model.Db2Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class MultipleDbEx {
    public static void main(String[] args) {
        System.out.println("Starting on the new database connection...");
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory1();
        addDummyData(factory);
        System.out.println("Finished the new database connection...");
    }

    static void addDummyData(EntityManagerFactory factory){
        System.out.println("Getting the new database connection Entity Manager...");
        EntityManager manager = factory.createEntityManager();
        try {
            System.out.println("Getting the new database connection transaction...");
            EntityTransaction transaction = manager.getTransaction();
            System.out.println("Beginning transaction...");
            transaction.begin();
            for (int i=1; i<=100; i++){
                System.out.println("Saving Record: " + i);
                Db2Model model = new Db2Model();
                model.setDummyField1("SomeString " + i);
                model.setDummyField2("Random String " + Math.random());
                model.setDummyField3(new Date());
                manager.persist(model);
            }
            System.out.println("Committing transaction...");
            transaction.commit();
            if(transaction.isActive())
                transaction.rollback();
            System.out.println("Added 100 records");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            System.out.println("Closing Entity Manager...");
            manager.close();
            System.out.println("Closing Entity Manager Factory...");
            factory.close();
        }
    }
}
