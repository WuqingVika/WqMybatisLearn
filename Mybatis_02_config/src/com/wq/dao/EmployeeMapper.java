package com.wq.dao;

import com.wq.bean.Employee;

/**
 * Created by qwu on 2018/8/26.
 */
public interface EmployeeMapper {
    public Employee selectEmployee(Integer id);
}
