<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/7/26
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form>
            <table border="1px">
                <tr>
                    <td>序号</td>
                    <td>姓名</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${studentList}" var="student" varStatus="index">
                    <tr>
                        <td>${index.index + 1}</td>
                        <td>${student.sname}</td>
                        <td>
                            <a href="delstudent/${student.sid}">删除</a>
                            <a href="updatepage/${student.sid}">修改</a>
                        </td>
                    </tr>
                </c:forEach>

                <a href="insertpage.jsp">添加</a>
            </table>
        </form>

<a href="excelout">导出数据</a>
</body>
</html>
