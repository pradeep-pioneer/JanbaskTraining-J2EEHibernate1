package com.janbask.training3.main;

import com.janbask.training3.misc.HibernateUtil;
import com.janbask.training3.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class HqlEx {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        //fromClause(factory);
        //selectClause(factory);
        //whereClause(factory);
        //orderByClause(factory);
        groupByClause(factory);
        //distinctKeyWord(factory);
        factory.close();
    }

    static void fromClause(SessionFactory factory){
        System.out.println("*******************************************************************************************************" +
                "\n**           FROM Clause Example                                                                     **" +
                "\n*******************************************************************************************************");
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Employee");
        for (Object item:query.getResultList()) {
            Employee employee = (Employee)item;
            System.out.println(String.format(
                    "\nEmployeeId: %s" +
                            "\n\tEmployee Name: %s" +
                            "\n\tRole: %s"
                    , employee.getId(), employee.getName(), employee.getRole()));
        }
        transaction.commit();
        session.close();
    }

    static void selectClause(SessionFactory factory){
        System.out.println(
                "*******************************************************************************************************" +
                "\n**           SELECT Clause Example                                                                   **" +
                "\n*******************************************************************************************************");
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT Emp.name, Emp.role, Emp.insertTime FROM Employee Emp");

        for (Object item:query.getResultList()) {
            Object[] items = (Object[])item;
            System.out.println(String.format("Name: %s\t\tRole: %s\tInserted On: %s", items[0], items[1], items[2]));
        }
        transaction.commit();
        session.close();
    }

    static void whereClause(SessionFactory factory){
        System.out.println(
                "*******************************************************************************************************" +
                "\n**           WHERE Clause Example                                                                    **" +
                "\n*******************************************************************************************************");
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Scanner scanner=new Scanner(System.in);
        System.out.print("\nEnter Employee Name you want to search for: ");
        String name = scanner.nextLine();
        Query query = session.createQuery("FROM Employee Emp WHERE Emp.name LIKE :employeeName");
        query.setParameter("employeeName", "%" + name + "%");
        System.out.println(String.format("Showing Employees with \"Name\" like %s", name));
        List items = query.getResultList();
        System.out.println(String.format("Found %s entries...", items.size()));
        for (Object item:items) {
            Employee employee = (Employee)item;
            System.out.println(String.format(
                    "\nEmployeeId: %s" +
                            "\n\tEmployee Name: %s" +
                            "\n\tRole: %s"
                    , employee.getId(), employee.getName(), employee.getRole()));
        }
        transaction.commit();
        session.close();
    }

    static void orderByClause(SessionFactory factory){
        System.out.println(
                "*******************************************************************************************************" +
                "\n**           ORDER BY Clause Example                                                                 **" +
                "\n*******************************************************************************************************");
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Employee Emp ORDER BY Emp.name DESC");
        for (Object item:query.getResultList()) {
            Employee employee = (Employee)item;
            System.out.println(String.format(
                    "\nEmployeeId: %s" +
                            "\n\tEmployee Name: %s" +
                            "\n\tRole: %s"
                    , employee.getId(), employee.getName(), employee.getRole()));
        }
        transaction.commit();
        session.close();
    }

    static void groupByClause(SessionFactory factory){
        System.out.println(
                "*******************************************************************************************************" +
                        "\n**           GROUP BY Clause Example                                                                 **" +
                        "\n*******************************************************************************************************");
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT Emp.name, COUNT(Emp.name) FROM Employee Emp GROUP BY Emp.name");
        List items = query.getResultList();
        for (Object item:query.getResultList()) {
            Object[] employee = (Object[]) item;
            System.out.println(String.format(
                    "\nName: %s" +
                            "\n\tEmployee Count: %s"
                    , employee[0], employee[1]));
        }
        transaction.commit();
        session.close();
    }

    static void distinctKeyWord(SessionFactory factory){
        System.out.println(
                "*******************************************************************************************************" +
                "\n**           DISTINCT Keyword Example                                                                **" +
                        "\n*******************************************************************************************************");
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT DISTINCT Emp.name FROM Employee Emp");
        System.out.println("Listing Distinct Employee Names");
        for (Object item:query.getResultList()) {
            System.out.println(item);
        }
        transaction.commit();
        session.close();
    }
}
