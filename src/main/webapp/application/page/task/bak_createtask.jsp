<!--<%=request.getRequestURI() %>-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="utf-8" />
    <title>创建新任务</title>
    <link href="../../css/style.css" rel="stylesheet" />
    <style>
        .main-section-body {
            padding-top: 50px;
            width: 450px;
            margin: auto;
        }

        .form-group {
            width: 100%;
        }
    </style>
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
            <li>
                <a href="../task/taskmanager.html">
                    <img src="../../img/l-manager.png" width="21" height="21" />
                    <span>任务管理</span>
                </a>
            </li>
            <li class="on">
                <a href="../task/createtask.html">
                    <img src="../../img/l-create.png" width="21" height="21" />
                    <span>创建新任务</span>
                </a>
            </li>
            <li>
                <a href="./tool.html">
                    <img src="../../img/l-tool.png" width="21" height="21" />
                    <span>分词工具</span>
                </a>
            </li>
            <li>
                <a href="./report.html">
                    <img src="../../img/l-report.png" width="21" height="21" />
                    <span>数据报告</span>
                </a>
            </li>
        </ul>
    </div>
    <div class="main">
        <div class="main-body">
            <div class="main-section">
                <div class="main-section-header">
                    按频道创建
                </div>
                <div class="main-section-body text-center">
                    <div class="form form-group">
                        <div class="form-title">
                            任务名称：
                        </div>
                        <div class="form-control form-control-long">
                            <input type="text" class="input" />
                        </div>
                    </div>
                    <div class="form form-group">
                        <div class="form-title">
                            任务周期：
                        </div>
                        <div class="form-control form-control-long">
                            <input type="text" class="input" />
                        </div>
                    </div>
                    <div class="form form-group">
                        <div class="form-title">
                            平台选择：
                        </div>
                        <div class="form-control">
                            <dis class="select">
                                <input class="root-select" type="text" readonly="readonly" data-value="-1" value="请选择平台" id="ddlSelectPlatform" />
                                <ul class="sub-select" style="display: none;">
                                    <li>
                                        <span data-value="0">汽车之家-论坛</span>
                                    </li>
                                    <li>
                                        <span data-value="1">易车网-论坛</span>
                                    </li>
                                    <li>
                                        <span data-value="2">新浪-论坛</span>
                                    </li>
                                    <li>
                                        <span data-value="3">网易-论坛</span>
                                    </li>
                                    <li>
                                        <span data-value="4">xxx-论坛</span>
                                    </li>
                                </ul>
                            </dis>
                            <a href="#" class="link">前往</a>
                        </div>
                    </div>
                    <div class="form form-group">
                        <div class="form-title">
                            频道：
                        </div>
                        <div class="form-control form-control-long">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>
                                            名称
                                        </td>
                                        <td style="width:100px;">
                                            ID
                                        </td>
                                        <td style="width:50px;">
                                            操作
                                        </td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            奥迪
                                        </td>
                                        <td>
                                            1111
                                        </td>
                                        <td>
                                            <a href="#" class="link">删除</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            奥迪
                                        </td>
                                        <td>
                                            1111
                                        </td>
                                        <td>
                                            <a href="#" class="link">删除</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            奥迪
                                        </td>
                                        <td>
                                            1111
                                        </td>
                                        <td>
                                            <a href="#" class="link">删除</a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="text-left">
                                <a href="#" class="link">新增</a><a href="#" class="link">导入已保存频道</a>
                            </div>
                        </div>

                    </div>
                    <div class="form form-submit">
                        <input type="button" class="btn btn-submit" value="创建" />
                        <input type="button" class="btn btn-cancel" value="清空" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../../js/jquery/jquery-1.11.1.min.js"></script>
    <script src="../../js/backbone/underscore-min.js"></script>
    <script src="../../js/backbone/backbone-min.js"></script>
    <script src="../../js/asset/common.js"></script>
    <script>
    </script>
</body>
</html>
