package com.wq.service;

import com.wq.bean.Employee;
import com.wq.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
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
    @Autowired
    private SqlSession sqlSession;
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Employee> getEmps(){
       //EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);//批量sqlSession
        /*employeeMapper.addEmp(new Employee("zengk","1","zeng@qq.ocm"));
        int i=1/0;*/
        return employeeMapper.selectEmps();
    }
}
