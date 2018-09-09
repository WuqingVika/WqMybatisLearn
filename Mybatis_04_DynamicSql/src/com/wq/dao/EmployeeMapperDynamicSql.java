package com.wq.dao;

import com.wq.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态sql
 * Created by qwu on 2018/9/6.
 */
public interface EmployeeMapperDynamicSql {
    public List<Employee> getEmpsByConditionIf(Employee employee);
    public List<Employee> getEmpsByConditionTrim(Employee employee);
    public List<Employee> getEmpsByConditionChoose(Employee employee);
    public void updateEmp(Employee employee);
    public List<Employee> getEmpsByForeach(@Param("ids") List<Integer> ids);
    public void addEmps(@Param("emps") List<Employee> emps);
}
