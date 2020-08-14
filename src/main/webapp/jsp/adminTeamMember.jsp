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
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                    <a href="queryTeamMember_one?tid=1">小组成员</a>
                    <a href='queryNoTeam'>无组成员</a>

                </div>
            </li>
            <li>
                <h4 class="M3"><span></span>业绩排行</h4>
                <div class="list-item none">
                    <a href=''>本月业绩</a>
                    <a href=''>年度业绩</a>
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




            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center;">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" id="team_one" name="1" class="team">
                                第 一 组
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <table class="table table-bordered table-striped" style="text-align: center">
                                <tr>
                                    <td>序号</td>
                                    <td>姓名</td>
                                    <td>入职日期</td>
                                    <td>操作</td>
                                </tr>
                                <c:forEach items="${list_member}" var="member" varStatus="index">
                                    <tr>
                                        <td>${index.index+1} </td>
                                        <td>${member.GNAME}</td>
                                        <td>${member.TDATE}</td>
                                        <td><a href="RemoveMemeber?gid=${member.GID}" title="删除">删除</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center;">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" id="team_two" name="2" class="team">
                                第 二 组

                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table table-bordered table-striped" style="text-align: center">
                                <tr>
                                    <td>序号</td>
                                    <td>姓名</td>
                                    <td>入职日期</td>
                                    <td>操作</td>
                                </tr>

                            </table>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center;">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" name="3" class="team">
                                第 三 组
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table table-bordered table-striped"  style="text-align: center">
                                <tr>
                                    <td>序号</td>
                                    <td>姓名</td>
                                    <td>入职日期</td>
                                    <td>操作</td>
                                </tr>

                            </table>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center;">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour" name="4" class="team">
                                第 四 组
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table table-bordered table-striped" style="text-align: center">
                                <tr>
                                    <td>序号</td>
                                    <td>姓名</td>
                                    <td>入职日期</td>
                                    <td>操作</td>
                                </tr>


                            </table>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center;">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive" id="team_five" name="5" class="team">
                                第 五 组
                                <input type="hidden" value="5" class="team_num">
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table table-bordered table-striped" style="text-align: center">
                                <tr >
                                    <td>序号</td>
                                    <td>姓名</td>
                                    <td>入职日期</td>
                                    <td>操作</td>
                                </tr>


                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>






</div>

<script>navList(12);</script>
<script type="text/javascript">
    $('.team').click(function(){

        var num=$(this).attr("name");
        $('table').empty();
        $.ajax({
            type : "post",
            async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : "<%=request.getContextPath() %>/queryTeamMember",
            data : {num:num},
            dataType : "json",
            success : function(data) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (data) {
                    for(var i=0;i<data.length;i++) {


                     $('table').append(
                            "<tr >" +
                            " <td>"+(i+1)+"</td>" +
                            "<td>"+data[i].GNAME +"</td>" +
                            "<td>"+data[i].TDATE+"</td>" +
                            "<td>"+"<a href='<%=request.getContextPath() %>/RemoveMemeber?gid="+data[i].GID+"'>移出此组</a>"+"</td>" +
                            "</tr>"
                        )

                    }

                }
            },
            error : function(errorMsg) {
                //请求失败时执行该函数


            }
        })
    });
</script>
</body>
</html>

