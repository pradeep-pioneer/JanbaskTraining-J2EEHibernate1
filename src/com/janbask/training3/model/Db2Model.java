package com.janbask.training3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DummyTable")
public class Db2Model implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", nullable=false, unique=true, length=11)
    private int id;

    @Column(name = "some_string", nullable = false)
    private String dummyField1;

    @Column(name = "random_string", nullable = true)
    private String dummyField2;
    @Column(name="inserted_on", nullable=true)
    private Date dummyField3;

    public String getDummyField1() {
        return dummyField1;
    }

    public void setDummyField1(String dummyField1) {
        this.dummyField1 = dummyField1;
    }

    public String getDummyField2() {
        return dummyField2;
    }

    public void setDummyField2(String dummyField2) {
        this.dummyField2 = dummyField2;
    }

    public Date getDummyField3() {
        return dummyField3;
    }

    public void setDummyField3(Date dummyField3) {
        this.dummyField3 = dummyField3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
