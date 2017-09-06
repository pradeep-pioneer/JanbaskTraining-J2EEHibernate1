package com.janbask.training3.main;

import com.janbask.training3.misc.HibernateUtil;
import com.janbask.training3.model.ExecutiveEmployee;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings("ALL")
public class CriteriaEx {
    private static SessionFactory factory;
    public static void main(String[] args) {
        try{
            factory = HibernateUtil.getSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        CriteriaEx ME = new CriteriaEx();

      /* Add few employee records in database */
        Integer empID1 = ME.addEmployee("Zara", "Ali", 6000);
        Integer empID2 = ME.addEmployee("Daisy", "Das", 8000);
        Integer empID3 = ME.addEmployee("John", "Paul", 2500);
        Integer empID4 = ME.addEmployee("Mohd", "Yasee", 1500);

      /* List down all the employees */
        ME.listEmployees();

      /* Print Total employee's count */
        ME.countEmployee();

      /* Print Toatl salary */
        ME.totalSalary();

        //Don't forget to close the factory or else the program won't terminate.
        if(factory!=null && factory.isOpen())
            factory.close();
    }
    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, int salary){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try{
            tx = session.beginTransaction();
            ExecutiveEmployee employee = new ExecutiveEmployee(fname, lname, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to  READ all the employees having salary more than 2000 */
    public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(ExecutiveEmployee.class);
            // Add restriction.
            cr.add(Restrictions.ge("salary", 2000));
            List employees = cr.list();

            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                ExecutiveEmployee employee = (ExecutiveEmployee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to print total number of records */
    public void countEmployee(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(ExecutiveEmployee.class);
            cr.add(Restrictions.ge("salary", 3000));
            // To get total row count.
            cr.setProjection(Projections.rowCount());
            List rowCount = cr.list();

            System.out.println("Total Coint: " + rowCount.get(0) );
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to print sum of salaries */
    public void totalSalary(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(ExecutiveEmployee.class);
            cr.add(Restrictions.ge("salary", 5000));
            // To get total salary.
            cr.setProjection(Projections.sum("salary"));
            List totalSalary = cr.list();

            System.out.println("Total Salary: " + totalSalary.get(0) );
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
