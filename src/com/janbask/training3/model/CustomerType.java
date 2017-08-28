package com.janbask.training3.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CustomerType")
public class CustomerType implements Serializable {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CustomerTypeId;

    @Column(name = "CustomerTypeName", unique = false, nullable = false, length = 100)
    private String customerTypeName;

    public Integer getCustomerTypeId() {
        return CustomerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        CustomerTypeId = customerTypeId;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }
}
