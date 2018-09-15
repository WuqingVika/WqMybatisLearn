package com.wq.dao;

import com.wq.bean.Employee;
import java.util.List;

/**
 * Created by qwu on 2018/8/26.
 */
public interface EmployeeMapper {
    //根据id查询一个员工
    public Employee selectEmployee(Integer id);
    public List<Employee> selectEmps();
    public void addEmp(Employee employee);
}
