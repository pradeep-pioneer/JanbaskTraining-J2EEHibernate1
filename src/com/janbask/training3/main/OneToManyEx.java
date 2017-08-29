package com.janbask.training3.main;

import com.janbask.training3.misc.HibernateUtil;
import com.janbask.training3.model.Account;
import com.janbask.training3.model.Employee;
import com.janbask.training3.model.EmployeeGroup;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Scanner;

public class OneToManyEx {

    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager manager = null;
        try {
            System.out.println("###One to Many Association Example###");
            Scanner scanner = new Scanner(System.in);

            factory = HibernateUtil.getEntityManagerFactory();
            manager = factory.createEntityManager();
            EmployeeGroup employeeGroup = new EmployeeGroup();
            System.out.println("\nEnter Group Name: ");
            String groupName = scanner.nextLine();
            employeeGroup.setEmployeeGroupName(groupName);
            EntityTransaction transaction = manager.getTransaction();
            Query query = manager.createQuery("FROM Employee");
            employeeGroup.setEmployees(query.getResultList());
            try {
                transaction.begin();
                manager.persist(employeeGroup);
                transaction.commit();
            }finally {
                if(transaction.isActive())
                    transaction.rollback();
            }
            System.out.println(String.format("\nCreated new Group:" +
                            "\nGroupId: %s" +
                            "\nGroupName: %s" +
                            "\nEmployee Details:", employeeGroup.getEmployeeGroupId(), employeeGroup.getEmployeeGroupName()));
            for (Employee employee:employeeGroup.getEmployees()) {
                System.out.println(String.format(
                        "\tId: %s" +
                        "\tName: %s" +
                        "\tRole: %s",
                        employee.getId(), employee.getName(), employee.getRole()));
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
}
