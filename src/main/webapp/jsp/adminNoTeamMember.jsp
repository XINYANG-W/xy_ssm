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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/images/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/menu.js"></script>
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
                    <a href='RemoveMemeber'>无组成员</a>

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
            <div class="modal-dialog modal-full" role="document">
            <table class="table table-bordered table-striped" style="text-align: center">
                <tr>
                    <td>序号</td>
                    <td>姓名</td>
                    <td>入职日期</td>

                    <td>操作</td>
                </tr>

                <c:forEach items="${list_NoTeame}" var="member" varStatus="index">
                    <tr>
                        <td>${index.index+1} </td>
                        <td>${member.GNAME}</td>
                        <td>${member.TDATE}</td>

                        <td>

                            <input type="button" id="btn_delete" value="删除" name="${member.GID}" >
                            <input type="hidden" id="GidStr" value="${member.GID}">
                            <input type="button" id="btn_add" value="移动" name="${member.GNAME}" class="">
                        </td>
                    </tr>
                </c:forEach>
            </table>


                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <form action="UpdateTeam" method="post">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                <h4 class="modal-title" id="myModalLabel">移动</h4>
                            </div>
                            <table >
                                <div class="modal-body" >
                                    <table >

                                        <div class="form-group">
                                            <label for="gname">姓名</label>
                                            <input type="text" value=""  readonly="readonly" id="gname">
                                            <input type="hidden" value="" id="gid" name="gid">
                                        </div>

                                        <div class="form-group">
                                            <label for="txt_departmentlevel">小组</label>
                                            <select id="txt_departmentlevel" style="width:300px;height: 30px;"class="form-control" name="team">
                                                <option value="0">请选择</option>
                                            </select>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                            <button type="submit" class="btn btn-primary">修改</button>
                                        </div>


                                    </table>

                                </div>
                            </table>
                        </div>
                        </form>
                    </div>

                </div>
        </div>
    </div>


</div>



</div>

<script>navList(12);</script>

<script type="text/javascript">
    $("#btn_add").click(function () {

        var s=this.name;
        var ss=$("#GidStr").val();

        $("#gname").val(s);
        $("#gid").val(ss);
        $.ajax({
            type : "post",
            async : true,
            url: "<%=request.getContextPath() %>/queryAllTeam",
            data : "",
            dataType : "json",
            success : function(data){
                for (var i = 0; i < data.length; i++) {
                    $("#txt_departmentlevel").append(

                        "<option value="+data[i].TID+">"+data[i].TNAME+"</option>"

                    )}




            }
        })


        $("#myModalLabel").text("新增");
        $('#myModal').modal();
    });


    $("#btn_delete").click(function () {
        var gid=this.name;

        window.location="<%=request.getContextPath() %>/delGid?gid="+gid+""
    })
</script>

</body>
</html>

