<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/8/5
  Time: 14:31
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
                    <a href='<%=request.getContextPath() %>/PsalesShowMonth'>本月业绩</a>
                    <a href=''>年度业绩</a>

                </div>
            </li>
            <li>
                <h4 class="M2"><span></span>人事变动</h4>
                <div class="list-item none">
                    <a href=''>小组成员</a>
                    <a href=''>无组成员</a>

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
            <table>
                <tr>
                    <td>序号</td>
                    <td>组名</td>
                    <td>业绩</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>一组</td>
                    <td>165000</td>
                </tr>
            </table>
            <div id="main2" style="width: 1000px;height:600px;"></div>
        </div>
    </div>






</div>

<script>navList(12);</script>
<script type="text/javascript">
    $(document).ready(function() {

        // 基于准备好的dom，初始化echarts实例
        var myChart2 = echarts.init(document.getElementById('main2'));
        // 指定图表的配置项和数据
        var names=[];
        var values=[];
        var datatemp = [];
        //数据加载完之前先显示一段简单的loading动画
        myChart2.showLoading();
        $.ajax({
            type : "post",
            async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : "PsalesCurrentMonthecharts",    //请求发送到dataActiont处
            data : {},
            dataType : "json",        //返回数据形式为json
            success : function(result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (result) {

                    for(var i=0;i<result.length;i++){
                        names.push(result[i].TNAME);
                        alert(names);
                        values.push(result[i].PNUM);



                    }
                    myChart2.hideLoading();    //隐藏加载动画
                    myChart2.setOption(
                        {
                            title: {
                                text: '销售业绩'
                            },
                            xAxis: {
                                type: 'category',
                                name: '小组',
                                data: names
                            },
                            yAxis: {
                                name: '业绩',
                                type: 'value'
                            },
                            series: [{
                                data: values,
                                type: 'bar',
                                showBackground: true,
                                backgroundStyle: {
                                    color: 'rgba(220, 220, 220, 0.8)'
                                }
                            }]
                        }
                    );
                }
            },
            error : function(errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myChart2.hideLoading();
            }
        });//end ajax

    });//刷新方法结束
</script>
</body>
</html>
