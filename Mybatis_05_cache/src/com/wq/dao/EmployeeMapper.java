package com.wq.dao;

import com.wq.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
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

    /**
     * 根据id和名称查询员工信息
     * @param id
     * @param lastName
     * @return
     */
    public Employee selectEmployeeByIdAndName(Integer id, String lastName);

    /**
     * 根据map参数查询员工信息
     * @param map
     * @return
     */
    public Employee selectEmployeeByMap(Map<String, Object> map);

    /**
     * 根据LastName模糊查询
     * @param lastName
     * @return
     */
    public List<Employee> selectEmployeeByLikeName(String lastName);

    /**
     * 根据LastName模糊查询并返回类型 为map
     * @param id
     * @return
     */
    public Map<String,Object> selectEmployeeReturnMap(Integer id);

    /**
     * 根据LastName模糊查询得到key为员工Id，值为员工信息的Map
     * @param lastName
     * @return
     */
    @MapKey("id")//id是javabean中的属性名
    public Map<Integer,Employee> selectEmployeeReturnMapE(String lastName);


    public List<Map<String,Object>> selectEmployeeReturnListMap(String lastName);
}
