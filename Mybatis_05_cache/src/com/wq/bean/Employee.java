package com.wq.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by qwu on 2018/8/26.
 */
/*@Alias("empWQ")*///批量起别名的情况下为防止重名
public class Employee implements Serializable{
    private Integer id;
    private String lastName;//
    private String gender;//
    private String email;//
    private Department dept;//

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Employee(Integer id, String lastName, String gender, String email, Department dept) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                '}';
    }

    public Employee(Integer id, String lastName, String gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Employee() {//无参构造得加上不然 Oracle查询的时候会报错
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
