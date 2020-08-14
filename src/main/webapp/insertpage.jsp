<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/7/26
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form action="uploadexcelpsales" method="post" enctype="multipart/form-data">
            <%--<input type="text" name="sname">--%>
                <input type="file" name="realpath">
            <input type="submit" value="提交">


        <%--</form>--%>

        <%--<form action="addStudents" method="post" >--%>
            <%--姓名<input type="text" name="sname">--%>
            <%--成绩<input type="texy" name="score">--%>
            <%--<input type="submit" value="登录">--%>


        <%--</form>--%>
    <a href="uploadexcel">导入数据</a>
</body>
</html>
