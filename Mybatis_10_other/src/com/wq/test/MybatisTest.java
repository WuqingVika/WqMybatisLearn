package com.wq.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wq.bean.EmpStatus;
import com.wq.bean.Employee;
import com.wq.bean.OraclePage;
import com.wq.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testMybatisDao() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            /*Page<Object> page = PageHelper.startPage(2, 4);
            List<Employee> list = mapper.selectEmps();
            list.forEach(System.out::println);
            System.out.println("当前页码："+page.getPageNum());
            System.out.println("总记录数："+page.getTotal());
            System.out.println("每页的记录数："+page.getPageSize());
            System.out.println("总页码："+page.getPages());*/
            //传入要连续显示多少页
            PageHelper.startPage(3, 2);
            List<Employee> list = mapper.selectEmps();
            //第二个参数代表要连续显示多少页
            //分页规则举例：pageNum=3，navigatePages=3,则对应显示2,3,4
            //pageNum=3，navigatePages=4,则对应显示1,2,3,4
            PageInfo<Employee> info = new PageInfo<>(list, 4);
            list.forEach(System.out::println);
            System.out.println("当前页码：" + info.getPageNum());
            System.out.println("总记录数：" + info.getTotal());
            System.out.println("每页的记录数：" + info.getPageSize());
            System.out.println("总页码：" + info.getPages());
            System.out.println("是否第一页：" + info.isIsFirstPage());
            System.out.println("连续显示的页码：");
            int[] nums = info.getNavigatepageNums();
            for (int i = 0; i < nums.length; i++) {
                System.out.println(nums[i]);
            }

        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testBatchDao() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);//代表批量执行
        long start = System.currentTimeMillis();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            for (int i = 0; i < 100; i++) {
                mapper.addEmps(new Employee(null, UUID.randomUUID().toString().substring(0, 5), "1", "wuqs"));
            }
            sqlSession.commit();
            long end = System.currentTimeMillis();
            System.out.println("执行时长：" + (end - start));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testOraclePageDao() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //List<Employee> list = mapper.selectEmpO();
            OraclePage page = new OraclePage();
            page.setStart(1);
            page.setEnd(3);
            mapper.getPageByProcedure(page);
            System.out.println("总记录数:" + page.getCount());
            System.out.println("分页记录数:" + page.getEmps().size());
            System.out.println("查询到的数据如下:");
            page.getEmps().forEach(System.out::println);
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testEnumUse(){
        EmpStatus e=EmpStatus.LOGOUT;
        System.out.println(e.ordinal());//索引
        System.out.println(e.name());//名字 mybatis默认不设置typeHandler存名字
    }

    @Test
    public void testEnumDao() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e=new Employee("testenum5","1","1");
           // mapper.addEmps(e);
            System.out.println("保存成功！==="+e.getId());
            //sqlSession.commit();
            System.out.println(mapper.selectEmployee(223).getEmpStatus());
        } finally {
            sqlSession.close();
        }
    }
}

