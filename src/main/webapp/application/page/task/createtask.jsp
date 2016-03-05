﻿<!--<%=request.getRequestURI() %>-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="utf-8" />
    <title>创建新任务</title>
    <link href="application/css/style.css" rel="stylesheet" />
    <style>
        .main-section-body {
            padding-top: 50px;
            width: 550px;
            margin: auto;
        }

        .form-group {
            width: 100%;
        }
    </style>
    <script>
        Config = {
        		BaseUrl: "<%=basePath %>application/",
                ServiceUrl:"<%=basePath %>",
            	currentUser: "xxxx@hylink.com"
        }
    </script>
</head>
<body>
    <header class="header">
        <div class="datavenus left">
            <img src="application/img/datavenus.png" width="176" height="31" />
        </div>
        <div class="info right">
            <ul class="info-list">
                <li>
                    <span class="account"></span>
                </li>
                <li>
                    <span class="line"></span>
                </li>
                <li>
                    <span class="logout">
                        <a href="" class="link">
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
                    <img src="application/img/l-manager.png" width="21" height="21" />
                    <span>任务管理</span>
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
                            <input type="text" class="input" data-bind="text:taskName" />
                        </div>
                    </div>
                    <div class="form form-group">
                        <div class="form-title">
                            任务周期：
                        </div>
                        <div class="form-control form-control-long">
                            <input class="datetime" data-bind="kendoDatePicker: startDate" />
                            <span class="line">--</span>
                            <input class="datetime" data-bind="kendoDatePicker: endDate" />
                        </div>
                    </div>
                    <div class="form form-group">
                        <div class="form-title">
                            平台选择：
                        </div>
                        <div class="form-control">
                            <div class="select">
                                <input class="root-select" data-bind="kendoDropDownList: { dataTextField: 'webName', dataValueField: 'webId', data: webList.data, value: webList.selected }" />
                            </div>
                            <a href="#" class="link" target="_blank" data-bind="attr:{'href':webList.link(webList.selected)}">前往</a>
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
                                <tbody data-bind="foreach:{ data: ForumList, as: 'forum' }">
                                    <tr>
                                        <td data-bind="text:forum.keyword,attr:{'contenteditable':true}"></td>
                                        <td data-bind="text:forum.forumId,attr:{'contenteditable':true}"></td>
                                        <!-- <td>
                                            <a href="#" data-bind="click:$root.DeleteForum">删除</a>
                                            <a href="#" data-bind="click:SubmitForum">确认</a>
                                        </td> -->
                                    </tr>
                                </tbody>
                            </table>
                            <div class="text-left">
                                <a href="#" class="link" data-bind="click:AddForum">新增</a>
                                <a href="#" class="link">导入已保存频道</a>
                            </div>
                        </div>

                    </div>
                    <div class="form form-submit">
                        <input type="button" class="btn btn-submit" data-bind="click:CreateTask" value="创建" />
                        <input type="button" class="btn btn-cancel" value="清空" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="application/js/requirejs/require.min.js"></script>
    <script type="text/javascript" src="application/js/requirejs/main.js"></script>
    <script>
        require(['common', 'app'], function (common, controller) {
            common.setCurrentInfo();
            controller.createTask();
            common.setLeftNavHeight();
        });
    </script>
</body>
</html>
