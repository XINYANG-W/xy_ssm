<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/8/4
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>销售业绩管理</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/images/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/menu.js"></script>
</head>

<body>
<div class="top"></div>
<div id="header">
    <div class="logo">销售业绩管理</div>
    <div class="navigation">
        <ul>
            <li>欢迎您！</li>
            <li><a href="">${logname }</a></li>
            <li><a href="<%=request.getContextPath() %>/exam/admin/updateAdminpass.jsp">修改密码</a></li>
            <li><a href="<%=request.getContextPath() %>/exam/admin/admin_index.jsp">退出</a></li>
        </ul>
    </div>
</div>
<div id="content">
    <div class="left_menu">
        <ul id="nav_dot">
            <li>
                <h4 class="M1"><span></span>公司业绩</h4>
                <div class="list-item none">
                    <a href="<%=request.getContextPath() %>/PsalesCurrentMonth">本月业绩</a>
                    <a href=''>年度业绩</a>

                </div>
            </li>
            <li>
                <h4 class="M2"><span></span>人事变动</h4>
                <div class="list-item none">
                    <a href='/queryTeamMember_one?tid=1'>小组成员</a>
                    <a href='queryNoTeam'>无组成员</a>

                </div>
            </li>
            <li>
                <h4 class="M3"><span></span>业绩排行</h4>
                <div class="list-item none">
                    <a href=''>月业绩</a>
                    <a href=''>年业绩</a>
                </div>
            </li>

        </ul>
    </div>
    <div class="m-right">
        <div class="right-nav">
            <ul>
                <li><img src="<%=request.getContextPath() %>/images/home.png"></li>
                <li style="margin-left:25px;">您当前的位置：</li>
                <li><a href="#">系统公告</a></li>
                <li>></li>
                <li><a href="#">最新公告</a></li>
            </ul>
        </div>
        <div class="main">


        </div>
    </div>






</div>

<script>navList(12);</script>
</body>
</html>

