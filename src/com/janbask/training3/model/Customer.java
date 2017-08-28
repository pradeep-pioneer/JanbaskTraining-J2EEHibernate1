package com.janbask.training3.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer customerId;

    @Column(name = "CustomerName", unique = false, nullable = false, length = 100)
    private String customerName;

    @ManyToOne(cascade = CascadeType.ALL)
    private CustomerType customerType;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
