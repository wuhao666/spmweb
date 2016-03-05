<!--<%=request.getRequestURI() %>-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="utf-8" />
    <title>任务详细信息</title>
    <link href="../../css/style.css" rel="stylesheet" />
    <script>
        var BaseUrl = "<%= basePath %>";
    </script>
</head>
<body>
    <header class="header">
        <div class="datavenus left">
            <img src="../../img/datavenus.png" width="176" height="31" />
        </div>
        <div class="info right">
            <ul class="info-list">
                <li>
                    <span class="account">xxxx@hylink.com</span>
                </li>
                <li>
                    <span class="line"></span>
                </li>
                <li>
                    <span class="logout">
                        <a href="<%=basePath%>/logout.do" class="link">
                            退出
                        </a>
                    </span>
                </li>
            </ul>
        </div>
    </header>
    <div class="left-nav">
        <ul>
            <li class="on">
                <a href="../task/taskmanager.html">
                    <img src="../../img/l-manager.png" width="21" height="21" />
                    <span>任务管理</span>
                </a>
            </li>
        </ul>
    </div>
    <div class="main">
        <div class="main-header">
            <span class="main-header-title">
                <input type="button" class="btn btn-submit" value="返回" />
            </span>
        </div>
        <div class="main-body" id="tab-main">
            <div class="main-section">
                <div class="main-section-header">
                    <img width="17" height="17" src="../../img/i-1.png" />任务概览
                </div>
                <div class="main-section-body text-center">
                    <table class="table">
                        <thead>
                            <tr>
                                <td>
                                    任务编号
                                </td>
                                <td>
                                    任务名称
                                </td>
                                <td>
                                    平台名称
                                </td>
                                <td>
                                    任务状态
                                </td>
                                <td>
                                    预估量级
                                </td>
                                <td>
                                    预估时间
                                </td>
                                <td>
                                    执行周期
                                </td>
                                <td>
                                    创建人
                                </td>
                                <td>
                                    创建时间
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="main-section">
                <div class="main-section-header">
                    <img width="17" height="17" src="../../img/i-2.png" />详细信息
                    <a href="#" class="link right">下载</a>
                </div>
                <div class="main-section-body text-center">
                    <div class="form form-top form-block text-right">
                        <span class="form-title">关键词：</span>
                        <span class="form-control">
                            <input type="text" class="" placeholder="请输入搜索关键词" />
                        </span>
                        <span class="form-control">
                            <input type="button" class="btn btn-submit" value="搜索" />
                        </span>
                    </div>
                    <table class="table">
                        <thead>
                            <tr>
                                <td>
                                    项目ID
                                </td>
                                <td>
                                    标题
                                </td>
                                <td>
                                    Url地址
                                </td>
                                <td>
                                    任务状态
                                </td>
                                <td>
                                    预估量级
                                </td>
                                <td>
                                    预估时间
                                </td>
                                <td>
                                    执行周期
                                </td>
                                <td>
                                    创建人
                                </td>
                                <td>
                                    创建时间
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C201512220001
                                </td>
                                <td>
                                    xxx新车上市舆情
                                </td>
                                <td>
                                    汽车之家-论坛
                                </td>
                                <td>
                                    1/56
                                </td>
                                <td>
                                    56000
                                </td>
                                <td>
                                    0天1小时
                                </td>
                                <td>
                                    20151001-20151030
                                </td>
                                <td>
                                    xxx@pagechoice.com
                                </td>
                                <td>
                                    2015-12-22
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="10">
                                    <div class="table-page">
                                        <a href="#">上一页</a>
                                        <a href="#">1</a>
                                        <span>...</span>
                                        <a href="#" class="on">40</a>
                                        <a href="#">41</a>
                                        <a href="#">42</a>
                                        <a href="#">43</a>
                                        <a href="#">44</a>
                                        <a href="#">45</a>
                                        <a href="#">46</a>
                                        <a href="#">47</a>
                                        <span>...</span>
                                        <a href="#">60</a>
                                        <a href="#">下一页</a>
                                    </div>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="../../js/requirejs/require.min.js"></script>
    <script type="text/javascript" src="../../js/requirejs/main.js"></script>
</body>
</html>
