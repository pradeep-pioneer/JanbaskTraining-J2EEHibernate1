package com.janbask.training3.main;

import com.janbask.training3.misc.HibernateUtil;
import com.janbask.training3.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Scanner;

public class HibernateCRUDEx {
    public static void main(String[] args) {
        retrieveEmployess();
        insertUpdateDeleteEmployee();
    }

    static void retrieveEmployess(){
        System.out.println("\n\n###Retrieve Example###");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Employee");
        System.out.println("\nListing Employees...");
        for (Object item: query.getResultList()) {
            Employee employee = (Employee) item;
            System.out.printf("\nID: %s\tEmployee Name: %s\tRole: %s", employee.getId(), employee.getName(), employee.getRole());
        }
        transaction.commit();

        session.close();
        System.out.println("\n###Retrieve Example Finished###");
    }

    static void insertUpdateDeleteEmployee(){
        System.out.println("\n\n###Insert Example###");
        System.out.println("Enter Employee Name: ");
        Scanner scanner = new Scanner(System.in);
        String employeeName = scanner.nextLine();
        System.out.println("Enter Employee Role: ");
        String role = scanner.nextLine();
        Employee employee = new Employee();
        employee.setName(employeeName);
        employee.setRole(role);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        System.out.println(String.format("New Employee Created!\nEmployeeId: %s", employee.getId()));
        System.out.println("###Insert Example Finished###\n\n");
        retrieveEmployess();
        System.out.println("###Update Example###");
        System.out.println("Enter Employee's New Name: ");
        employeeName = scanner.nextLine();
        employee.setName(employeeName);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(employee);
        transaction.commit();
        System.out.println(String.format("Employee Updated!\nEmployeeId: %s\tEmployeeName: %s\tRole: %s", employee.getId(), employee.getName(), employee.getRole()));
        System.out.println("###Update Example Finished###\n\n");
        retrieveEmployess();
        System.out.println("###Delete Example###");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
        session.delete(employee);
        transaction.commit();
        System.out.println(String.format("Employee Deleted!\nEmployeeId: %s\tEmployeeName: %s\tRole: %s", employee.getId(), employee.getName(), employee.getRole()));
        System.out.println("###Delete Example Finished###\n\n");
        retrieveEmployess();
        session.close();
    }

    //ToDo: Write a function to delete all employees
}
