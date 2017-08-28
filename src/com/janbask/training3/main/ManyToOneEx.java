package com.janbask.training3.main;

import com.janbask.training3.misc.HibernateUtil;
import com.janbask.training3.model.Customer;
import com.janbask.training3.model.CustomerType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Scanner;

public class ManyToOneEx {

    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        try {
            factory = HibernateUtil.getEntityManager();
            checkAndCreateCustomerTypes(factory);
            createCustomer(factory);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (factory != null && factory.isOpen())
                factory.close();
        }
    }

    static void createCustomer(EntityManagerFactory factory){
        System.out.println("\n###Create New Customer###");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter Customer Name: ");
        String name = scanner.nextLine();
        displayCustomerTypes(factory);
        System.out.println("\nEnter Customer Type Id: ");
        Integer customerTypeId = scanner.nextInt();
        EntityManager manager = null;
        Customer customer = new Customer();
        customer.setCustomerName(name);
        try{
            manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            CustomerType customerType = manager.find(CustomerType.class, customerTypeId);
            customer.setCustomerType(customerType);
            try {
                manager.persist(customer);
                transaction.commit();
                System.out.println("\nCustomer Created!");
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                if(transaction.isActive())
                    transaction.rollback();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            if(manager!=null && manager.isOpen())
                manager.close();
        }
    }

    static void displayCustomerTypes(EntityManagerFactory factory){
        System.out.println("###Listing Customer Types###");
        EntityManager manager = null;
        try{
            manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                Query query = manager.createQuery("FROM CustomerType");
                for (Object item: query.getResultList()) {
                    CustomerType customerType = (CustomerType)item;
                    System.out.println(String.format(
                            "\nId: %s" +
                                    "\tCustomer Type Name: %s",
                            customerType.getCustomerTypeId(), customerType.getCustomerTypeName()));
                }
                transaction.commit();
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                if(transaction.isActive())
                    transaction.rollback();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            if(manager!=null && manager.isOpen())
                manager.close();
        }
    }
    static void checkAndCreateCustomerTypes(EntityManagerFactory factory){
        System.out.println("###Generating Customer Types - If not found###");
        EntityManager manager = null;
        try{
            manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                Query query = manager.createQuery("FROM CustomerType");
                if(query.getResultList().size()==0) {
                    CustomerType basic = new CustomerType();
                    basic.setCustomerTypeName("Basic");
                    CustomerType premium = new CustomerType();
                    premium.setCustomerTypeName("Premium");
                    manager.persist(basic);
                    manager.persist(premium);
                }
                transaction.commit();
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                if(transaction.isActive())
                    transaction.rollback();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            if(manager!=null && manager.isOpen())
                manager.close();
            System.out.println("\n#####Customer Types Ready#####");
        }
    }
}
