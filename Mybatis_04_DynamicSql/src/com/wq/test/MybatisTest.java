package com.wq.test;

import com.wq.bean.Department;
import com.wq.bean.Employee;
import com.wq.dao.EmployeeMapperDynamicSql;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * 1、接口式编程
 * 原生：		Dao		====>  DaoImpl
 * mybatis：	Mapper	====>  xxMapper.xml
 * <p>
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * （将接口和xml进行绑定）
 * EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * sql映射文件：保存了每一个sql语句的映射信息：
 * 将sql抽取出来。
 * <p>
 * Created by qwu on 2018/8/26.
 */
public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testEmployeeMapperDynamicSql() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            Employee emp = new Employee(null, null, null, null, null);
            //List<Employee> emps = mapper.getEmpsByConditionIf(emp);
            //List<Employee> emps = mapper.getEmpsByConditionTrim(emp);
            List<Employee> emps = mapper.getEmpsByConditionChoose(emp);
            emps.forEach(System.out::println);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testEmployeeMapperDynamicSqlUpdate() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            Employee emp = new Employee(3, "吴庆2", null, null, null);
            mapper.updateEmp(emp);
            List<Employee> emps = mapper.getEmpsByConditionChoose(emp);
            emps.forEach(System.out::println);

        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
    //getEmpsByForeach

    @Test
    public void testEmpsByForeach() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            List<Employee> emps = mapper.getEmpsByForeach(Arrays.asList(1, 2, 3, 6));
            emps.forEach(System.out::println);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testForEachInsert() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            List<Employee> emps=new ArrayList<>();
            emps.add(new Employee(null,"wqq3","1","wq@dd.com",new Department(1)));
            emps.add(new Employee(null,"wq14","2","wq@ddee.com",new Department(2)));
             mapper.addEmps(emps);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
