package com.wq.bean;

import java.util.List;

/**
 * Created by qwu on 2018/9/5.
 */
public class Department {
    private Integer id;
    private String deptName;
    private List<Employee> emps;

    public Department(Integer id, String deptName, List<Employee> emps) {
        this.id = id;
        this.deptName = deptName;
        this.emps = emps;
    }

    public Department(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", emps=" + emps +
                '}';
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
