package com.janbask.training3.main;

import com.janbask.training3.misc.HibernateUtil;
import com.janbask.training3.model.Account;
import com.janbask.training3.model.Employee;
import com.janbask.training3.model.EmployeeJpa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Scanner;
import java.util.function.Predicate;

//ToDo: Never forget to close the session factories - they leak resources if not closed.
public class OneToOneExample {
    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager manager = null;
        try {
            System.out.println("###One to One Association Example###");
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease enter the Account Details Below");
            System.out.print("AccountNumber: ");
            String accountNumber = scanner.nextLine();
            retrieveEmployess();
            System.out.println("\nEmployeeId: ");
            Integer id = scanner.nextInt();
            factory = HibernateUtil.getEntityManagerFactory();
            manager = factory.createEntityManager();
            Employee employee = manager.find(Employee.class, id);
            if (employee != null) {
                Account account = new Account();
                account.setAccountNumber(accountNumber);
                account.setEmployee(employee);
                EntityTransaction transaction = manager.getTransaction();

                try {
                    transaction.begin();
                    manager.persist(account);
                    transaction.commit();
                }finally {
                    if(transaction.isActive())
                        transaction.rollback();
                }
                System.out.println(String.format("\nCreated new Account:" +
                                "\nAccountId: %s" +
                                "\nAccountNumber: %s" +
                                "\nEmployeeDetails:" +
                                "\n\tEmployeeId: %s" +
                                "\n\tEmployeeName: %s" +
                                "\n\tRole: %s", account.getAccountId(), account.getAccountNumber(),
                        account.getEmployee().getId(), account.getEmployee().getName(), account.getEmployee().getRole()));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if (manager != null && manager.isOpen()) {
                    manager.close();
                    System.out.println("\nClosed the Entity Manager!");
                }
                if (factory != null && factory.isOpen()) {
                    factory.close();
                    System.out.println("\nClosed the Entity Manager Factory!");
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                System.out.println("\nEverything Closed!!!");
            }
        }
    }

    static void retrieveEmployess(){
        System.out.println("\n\n### Employee List ###");
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Employee");
        System.out.println("\nListing Employees...");
        for (Object item: query.getResultList()) {
            Employee employee = (Employee) item;
            System.out.printf("\nID: %s\tEmployee Name: %s\tRole: %s", employee.getId(), employee.getName(), employee.getRole());
        }
        transaction.commit();

        session.close();
        factory.close();
        System.out.println("\n######################################################################");
    }
}
