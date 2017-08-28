package com.janbask.training3.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Account")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer accountId;

    @Column(name = "ACC_NO", unique = false, nullable = false, length = 100)
    private String accountNumber;

    //We will define the association here
    @OneToOne
    Employee employee;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer value){
        accountId=value;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
