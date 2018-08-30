package com.wq.test;

import com.wq.bean.Employee;
import com.wq.dao.EmployeeMapper;
import com.wq.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * 1、接口式编程
 * 	原生：		Dao		====>  DaoImpl
 * 	mybatis：	Mapper	====>  xxMapper.xml
 *
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 		（将接口和xml进行绑定）
 * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * 		sql映射文件：保存了每一个sql语句的映射信息：
 * 					将sql抽取出来。
 *
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
        SqlSessionFactory sqlSessionFactory =getSqlSessionFactory();/*new SqlSessionFactoryBuilder().build(inputStream);*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("com.wq.dao.EmployeeMapper.selectEmployee", 1);//1代表参数
            System.out.println(employee);
        }finally {
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
     * 		1）、根据全局配置文件得到SqlSessionFactory；
     * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
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
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 添加注解的方式写sql
     * @throws IOException
     */
    @Test
    public void testMybatisAnnotationDao() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            //会为接口自动创建一个代理对象,由代理对象来执行增删改操作
            Employee employee = mapper.selectEmployee(1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }
}
