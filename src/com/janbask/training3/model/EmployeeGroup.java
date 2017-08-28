package com.janbask.training3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EmployeeGroup")
public class EmployeeGroup implements Serializable{
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer employeeGroupId;

    @Column(name = "GROUP_NAME", unique = false, nullable = false, length = 100)
    private String employeeGroupName;

    @OneToMany
    private List<Employee> employees=new ArrayList<>();

    public Integer getEmployeeGroupId() {
        return employeeGroupId;
    }

    public void setEmployeeGroupId(Integer employeeGroupId) {
        this.employeeGroupId = employeeGroupId;
    }

    public String getEmployeeGroupName() {
        return employeeGroupName;
    }

    public void setEmployeeGroupName(String employeeGroupName) {
        this.employeeGroupName = employeeGroupName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
