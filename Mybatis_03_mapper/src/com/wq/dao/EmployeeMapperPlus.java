package com.wq.dao;

import com.wq.bean.Employee;

import java.util.List;

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

    /**
     * 分步查询出员工信息、部门信息
     * @param id
     * @return
     */
    public Employee selectEmpAndDeptByStep(Integer id);

    /**
     * 根据部门Id查询员工信息
     * @param deptId
     * @return
     */
    public List<Employee> getEmpsByDeptId(Integer deptId);
}
