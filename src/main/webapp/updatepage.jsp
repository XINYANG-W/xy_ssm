<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/7/26
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form action="${pageContext.request.contextPath}/updatestudent" method="post" >
          <%--  <table>
                <tr>
                    <td>姓名</td>
                </tr>
                <tr>
                    <td>--%>
                        <input type="text" name="sname" value="${student1.sname}">
                        <input type="text" name="sid" value="${student1.sid}">
                  <input type="submit" value="修改">
              <%--         </td>

             </tr>
            &lt;%&ndash; <input type="submit" value="修改">&ndash;%&gt;
         </table>--%>
        </form>
</body>
</html>
