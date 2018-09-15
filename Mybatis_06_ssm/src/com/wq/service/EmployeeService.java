package com.wq.service;

import com.wq.bean.Employee;
import com.wq.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qwu on 2018/9/15.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Employee> getEmps(){
        /*employeeMapper.addEmp(new Employee("zengk","1","zeng@qq.ocm"));
        int i=1/0;*/
        return employeeMapper.selectEmps();
    }
}
