package com.wq.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wq.bean.Employee;
import com.wq.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
            PageHelper.startPage(3,2);
            List<Employee> list = mapper.selectEmps();
            //第二个参数代表要连续显示多少页
            //分页规则举例：pageNum=3，navigatePages=3,则对应显示2,3,4
            //pageNum=3，navigatePages=4,则对应显示1,2,3,4

            PageInfo<Employee> info = new PageInfo<>(list, 4);
            list.forEach(System.out::println);
            System.out.println("当前页码："+info.getPageNum());
            System.out.println("总记录数："+info.getTotal());
            System.out.println("每页的记录数："+info.getPageSize());
            System.out.println("总页码："+info.getPages());
            System.out.println("是否第一页："+info.isIsFirstPage());
            System.out.println("连续显示的页码：");
            int[] nums = info.getNavigatepageNums();
            for (int i = 0; i < nums.length; i++) {
                System.out.println(nums[i]);
            }

        }finally {
            sqlSession.close();
        }
    }
}
