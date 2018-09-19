package com.wq.bean;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qwu on 2018/9/19.
 */
public class EmpStatusTypeHandler implements TypeHandler<EmpStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, EmpStatus empStatus, JdbcType jdbcType) throws SQLException {
        System.out.println("要保存的状态码："+empStatus.getCode());
        ps.setString(i, empStatus.getCode().toString());
    }

    @Override
    public EmpStatus getResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        System.out.println("2从数据库中获取的状态码："+code);
        EmpStatus empStatus=EmpStatus.getEmpStatusByCode(code);
        return empStatus;
    }

    @Override
    public EmpStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        System.out.println("3从数据库中获取的状态码："+code);
        EmpStatus empStatus=EmpStatus.getEmpStatusByCode(code);
        return empStatus;
    }

    @Override
    public EmpStatus getResult(CallableStatement cs, int i) throws SQLException {
        int code = cs.getInt(i);
        System.out.println("4从数据库中获取的状态码："+code);
        EmpStatus empStatus=EmpStatus.getEmpStatusByCode(code);
        return empStatus;
    }
}
