package com.wq.test;

import com.wq.bean.Department;
import com.wq.bean.Employee;
import com.wq.dao.DepartmentMapper;
import com.wq.dao.EmployeeMapper;
import com.wq.dao.EmployeeMapperAnnotation;
import com.wq.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void testMybatis() throws IOException {
       /* String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);*/
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();/*new SqlSessionFactoryBuilder().build(inputStream);*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("com.wq.dao.EmployeeMapper.selectEmployee", 1);//1代表参数
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 1.获取到SqlSessionFactory
     *2.获取SqlSession对象
     * 3.获取接口的实现类对象
     */
    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3、将sql映射文件注册在全局配置文件中
     * 4、写代码：
     * 1）、根据全局配置文件得到SqlSessionFactory；
     * 2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     * 一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     *
     * @throws IOException
     */
    @Test
    public void testMybatisDao() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //会为接口自动创建一个代理对象,由代理对象来执行增删改操作
            Employee employee = mapper.selectEmployee(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 添加注解的方式写sql
     *
     * @throws IOException
     */
    @Test
    public void testMybatisAnnotationDao() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            //会为接口自动创建一个代理对象,由代理对象来执行增删改操作
            Employee employee = mapper.selectEmployee(3);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    //测试增删改
    @Test
    public void testZsg() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //会为接口自动创建一个代理对象,由代理对象来执行增删改操作
            //1-----------------增加
            //oracle添加如果为空的 需要全局配置jdbcType=NULL 因为Mybatis默认识别空用JdbcType Other来作为类型，但是呢Oracle不支持Other，所以需要改为NuLL，要么在属性加Null 要么全局加
            Employee employee = new Employee(null, "田喜碧339", "1", null);
            mapper.addEmployee(employee);
            System.out.println(employee);

            //2-----------------修改
            /*Employee employee = new Employee(1,"田喜碧2","1","hebe0330@22hhh.com");
            Integer i = mapper.updateEmployee(employee);
            System.out.println(i);//更新0条记录返回0*/

            //3-----------------删除
           /* Boolean f = mapper.deleteEmployee(1);
            System.out.println(f);//删除失败返回false*/

        } finally {
            sqlSession.commit();//因为是涉及增删改的 所以要手动提交
            sqlSession.close();
        }
    }

    //测试参数处理
    @Test
    public void testGetEmp() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //1.=============多个参数
            /*Employee employee = mapper.selectEmployeeByIdAndName(2, "杨幂");*/
            //2.=============Map
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 2);
            map.put("lastName", "杨幂");
            map.put("tableName", "tbl_employee");
            Employee employee = mapper.selectEmployeeByMap(map);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test//测试返回列表
    public void testGetEmpList() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> empList = mapper.selectEmployeeByLikeName("田%");
            empList.forEach(System.out::println);
        } finally {
            sqlSession.close();
        }
    }
    //
    @Test//测试返回Map
    public void testEmployeeReturnMap() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.selectEmployeeReturnMap(2);
            System.out.println(map);
        } finally {
            sqlSession.close();
        }
    }

    @Test//测试返回Map集合
    public void testEmployeeReturnMapList() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Map<String, Object>> list = mapper.selectEmployeeReturnListMap("%喜碧%");
            list.forEach(System.out::println);
        } finally {
            sqlSession.close();
        }
    }
    @Test//测试返回Map:key是员工ID，value是员工信息
    public void testEmployeeReturnMap2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> maps = mapper.selectEmployeeReturnMapE("%喜碧%");
            System.out.println(maps);
        } finally {
            sqlSession.close();
        }
    }

    @Test//测试ResultMap
    public void testResultMap() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.selectEmpById(6);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test//测试ResultMap
    public void testResultMapPlus() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.selectEmpAndDeptById(3);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test//测试分步查询
    public void testSearchEmpAndDeptStep() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.selectEmpAndDeptByStep(2);
            System.out.println(employee.getLastName());
            System.out.println(employee.getDept());//需要部门的时候 才会加载部门信息

        } finally {
            sqlSession.close();
        }
    }

    //===================================================================
    @Test//测试部门查询（里面的员工列表也要显示）
    public void testGetDeptPlus() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptByIdPlus(1);
            System.out.println(department);
            //System.out.println(department.getDeptName());
        } finally {
            sqlSession.close();
        }
    }

    @Test//测试部门分步懒加载查询
    public void testGetDeptPlusStep() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptByIdStep(1);
            //System.out.println(department);
            System.out.println(department.getDeptName());
        } finally {
            sqlSession.close();
        }
    }
}
