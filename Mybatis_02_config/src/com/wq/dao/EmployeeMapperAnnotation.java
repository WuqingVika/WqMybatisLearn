package com.wq.dao;

import com.wq.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * Created by qwu on 2018/8/26.
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id = #{id}")
    public Employee selectEmployee(Integer id);
}
