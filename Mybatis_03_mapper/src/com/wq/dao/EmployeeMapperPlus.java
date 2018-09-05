package com.wq.dao;

import com.wq.bean.Employee;

/**
 * Created by qwu on 2018/9/5.
 */
public interface EmployeeMapperPlus {
    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    public Employee selectEmpById(Integer id);

    /**
     * 根据id查询员工及其部门信息
     * @param id
     * @return
     */
    public Employee selectEmpAndDeptById(Integer id);
}
