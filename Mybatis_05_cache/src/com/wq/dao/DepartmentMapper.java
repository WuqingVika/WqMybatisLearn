package com.wq.dao;

import com.wq.bean.Department;

/**
 * Created by qwu on 2018/9/6.
 */
public interface DepartmentMapper {
    /**
     * 根据id查询出部门信息
     * @param id
     * @return
     */
    public Department getDeptById(Integer id);

    /**
     * 根据Id查询出部门信息以及该部门下的员工信息
     * @param id
     * @return
     */
    public Department getDeptByIdPlus(Integer id);

    /**
     * 分步查询
     * @param id
     * @return
     */
    public Department getDeptByIdStep(Integer id);
}
