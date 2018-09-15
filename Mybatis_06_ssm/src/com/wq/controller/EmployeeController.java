package com.wq.controller;

import com.wq.bean.Employee;
import com.wq.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by qwu on 2018/9/15.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getAllEmps")
    public String getEmps(Map<String,Object> map){
        List<Employee> emps = employeeService.getEmps();
        map.put("emps",emps);
        return "list";
    }
}
