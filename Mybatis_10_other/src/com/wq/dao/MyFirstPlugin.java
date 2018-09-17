package com.wq.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * 插件
 * Created by qwu on 2018/9/16.
 */

/**
 * 完成插件签名：
 * 告诉MyBatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts(
        {
                @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
        })

/**
 * intercept：拦截：
 * 		拦截目标对象的目标方法的执行；
 */
public class MyFirstPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin...intercept:" + invocation.getMethod());
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象：" + target);
        //偷梁换柱开始啦 比如原本查的是3号员工，我们现在把它偷偷改为查12号员工
        MetaObject metaObject = SystemMetaObject.forObject(target);//拿到要拦截对象的元数据
        Object value = metaObject.getValue("parameterHandler.parameterObject");//StatementHandler==>ParameterHandler===>parameterObject
        System.out.println("sql语句用的一开始传入的参数是："+value);
        metaObject.setValue("parameterHandler.parameterObject",12);
        //执行目标方法
        Object proceed = invocation.proceed();//放行了 目标方法 才能执行
        //返回执行后的返回值
        return proceed;
    }


    /**
     * plugin：
     * 包装目标对象的：包装：为目标对象创建一个代理对象
     */
    @Override
    public Object plugin(Object target) {
        // TODO Auto-generated method stub
        //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
        System.out.println("MyFirstPlugin...plugin:mybatis将要包装的对象" + target);
        Object wrap = Plugin.wrap(target, this);
        //返回为当前target创建的动态代理
        return wrap;
    }

    /**
     * setProperties：
     * 将插件注册时 的property属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        System.out.println("插件配置的信息：" + properties);
    }
}
