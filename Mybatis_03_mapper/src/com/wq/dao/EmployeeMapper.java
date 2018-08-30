package com.wq.dao;

import com.wq.bean.Employee;

import java.util.Map;

/**
 * Created by qwu on 2018/8/26.
 */
public interface EmployeeMapper {
    //根据id查询一个员工
    public Employee selectEmployee(Integer id);
    //增
    public Integer addEmployee(Employee employee);
    //改
    public Integer updateEmployee(Employee employee);
    //删
    public Boolean deleteEmployee(Integer id);

    public Employee selectEmployeeByIdAndName(Integer id,String lastName);

    public Employee selectEmployeeByMap(Map<String,Object> map);

}
