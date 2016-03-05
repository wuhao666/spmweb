<!--<%=request.getRequestURI() %>-->
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
    <title>任务管理</title>
    <link href="application/css/style.css" rel="stylesheet" />
    <script>
        Config = {
            BaseUrl: "<%=basePath %>",
            currentUser: "xxxx@hylink.com",
            logoutUrl: function () {
                return Config.BaseUrl + "logout.do";
            }
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
                <a href="<%=basePath %>lolthTask/taskmanagerPage.do">
                    <img src="application/img/l-manager.png" width="21" height="21" />
                    <span>任务管理</span>
                </a>
            </li>
        </ul>
    </div>
    <div class="main">
        <div class="main-header">
            <div class="main-header-title">
                <span class="text-left">
                    <input type="button" class="btn btn-submit" value="创建新任务" />
                </span>
                <span class="right form form-top">
                    <span class="form-title">名称/编号：</span>
                    <span class="form-control">
                        <input type="text" placeholder="请输入任务名称或编号" value="" />
                    </span>
                    <span class="form-title">创建时间：</span>
                    <span class="form-control">
                        <input type="text" />-<input type="text" />
                    </span>
                    <span class="form-control">
                        <input type="button" class="btn btn-submit" value="搜索" />
                    </span>
                </span>
            </div>
        </div>
        <div class="main-body">
            <div class="main-section">
                <div class="main-section-body text-center">
                    <table class="table" id="TaskList">
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
                                    执行周期
                                </td>
                                <td>
                                    创建人
                                </td>
                                <td>
                                    创建时间
                                </td>
                                <td>
                                    操作
                                </td>
                            </tr>
                        </thead>
                        <tbody data-bind="foreach:{ data: taskList, as: 'task' }">
                            <tr>
                                <td data-bind="text:task.taskId"></td>
                                <td data-bind="text:task.taskName"></td>
                                <td data-bind="text:task.webName"></td>
                                <td data-bind="text:task.taskNums"></td>
                                <td data-bind="text:task.dataNums"></td>
                                <td>
                                    <span data-bind="text:task.startDate"></span>
                                    -
                                    <span data-bind="text:task.endDate"></span>
                                </td>
                                <td data-bind="text:task.createUser"></td>
                                <td data-bind="text:task.createDate"></td>
                                <td class="td-operation">
                                    <ul data-bind="foreach:{ data: task.lolthTaskDetail, as: 'detail' }">
                                        <li>
                                            <span>
                                                <a href="#" class="link" data-bind="text:detail.tableNameCn,attr:{'data-tableName':detail.tableName,'data-classBean':detail.classBean}"></a>
                                            </span>
                                            <span data-bind="visible:detail.downloadUrl" class="line"></span>
                                            <span data-bind="if:detail.downloadUrl">
                                                <a href="#" data-bind="attr:{href:detail.downloadUrl}" class="link">导出</a>
                                            </span>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="9">
                                    <div class="table-page">
                                        <a href="#" data-bind="click:PrePage">上一页</a>
                                        <a href="#" data-bind="click:FirstPage">1</a>
                                        <span>...</span>
                                        <a href="#" class="on" data-bind="click:GetPage">40</a>
                                        <a href="#" data-bind="click:GetPage">41</a>
                                        <a href="#" data-bind="click:GetPage">42</a>
                                        <a href="#" data-bind="click:GetPage">43</a>
                                        <a href="#" data-bind="click:GetPage">44</a>
                                        <a href="#" data-bind="click:GetPage">45</a>
                                        <a href="#" data-bind="click:GetPage">46</a>
                                        <a href="#" data-bind="click:GetPage">47</a>
                                        <span>...</span>
                                        <a href="#" data-bind="click:LastPage">60</a>
                                        <a href="#" data-bind="click:NextPage">下一页</a>
                                    </div>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="application/js/requirejs/require.min.js"></script>
    <script type="text/javascript" src="application/js/requirejs/main.js"></script>
    <script>
        require(['common', 'view-model'], function (common, controller) {

            common.setCurrentInfo();

            var option = GetOption();
            controller.getTaskManager(option);

            common.setLeftNavHeight();

            function GetOption() {
                var option = {};

                option.search = {};
                option.search.name = '';
                option.search.startDate = '';
                option.search.endDate = '';

                option.page = {};
                option.page.pageSize = "10";
                option.page.currentPage = "1";

                return option;
            }
        });
    </script>
</body>
</html>
