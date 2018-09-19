package com.wq.dao;

import com.wq.bean.Employee;
import com.wq.bean.OraclePage;

import java.util.List;

/**
 * Created by qwu on 2018/8/26.
 */
public interface EmployeeMapper {
    public Employee selectEmployee(Integer id);
    public List<Employee> selectEmps();
    public List<Employee> selectEmpO();
    Long addEmps(Employee employee);
    public void getPageByProcedure(OraclePage page);
}
