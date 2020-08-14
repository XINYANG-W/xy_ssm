<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/7/26
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="selstudent">查询</a>

<form action="addManager" method="post" >
    <input type="text" name="mname">
    <input type="password" name="mpass">
    <input type="submit" value="添加">


</form>

<form action="login" method="post" >
    <input type="text" name="mname">
    <input type="password" name="mpass">
    <input type="submit" value="登录">


</form>
<a href="showecharts.html">饼图</a>
<a href="jsp/adminManager.jsp">测试</a>
<a href="exceloutPsales">excel</a>
<a href="uploadexcelpsales">exceldown</a>


</body>
</html>
