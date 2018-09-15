<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/15
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表 </title>
</head>
<body>
  <table border="1">
      <tr>
          <th>员工id</th>
          <th>员工姓名</th>
          <th>邮箱</th>
      </tr>
      <c:forEach items="${emps}" var="emp">
          <tr>
              <td>${emp.id}</td>
              <td>${emp.lastName}</td>
              <td>${emp.email}</td>
          </tr>
      </c:forEach>
  </table>
</body>
</html>
