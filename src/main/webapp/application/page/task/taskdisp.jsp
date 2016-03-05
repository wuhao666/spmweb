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
    <title>任务详细信息</title>
    <link href="application/css/style.css" rel="stylesheet" />
    <script>
        Config = {
            BaseUrl: "http://localhost:18568/",
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
        <div class="main-header">
            <span class="main-header-title">
                <input type="button" class="btn btn-submit" data-bind="click:returnPage" value="返回" />
            </span>
        </div>
        <div class="main-body" id="tab-main">
            <div class="main-section">
                <div class="main-section-header">
                    <img width="17" height="17" src="application/img/i-1.png" />任务概览
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
                                <td data-bind="text:task.taskId"></td>
                                <td data-bind="text:task.taskName"></td>
                                <td>
                                    <span data-bind="text:task.webName"></span>
                                    -
                                    <span data-bind="text:task.webModule"></span>
                                </td>
                                <td data-bind="text:task.taskNums"></td>
                                <td data-bind="text:task.dataNums"></td>
                                <td>
                                    <span data-bind="text:task.startDate"></span>
                                    -
                                    <span data-bind="text:task.endDate"></span>
                                </td>
                                <td data-bind="text:task.createUser"></td>
                                <td data-bind="text:task.createDate"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="main-section">
                <div class="main-section-header">
                    <img width="17" height="17" src="application/img/i-2.png" />详细信息
                    <a class="link right" data-bind="attr:{'href':task.downloadUrl}">下载</a>
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
                            <tr data-bind="foreach:{ data: columns, as: 'column' }">
                                <td data-bind="text:column.title"></td>
                            </tr>
                        </thead>
                        <tbody data-bind="foreach:{ data: detailList, as: 'detail' }">
                            <tr>
                                <td data-bind="text:detail.id"></td>
                                <td data-bind="text:detail.title"></td>
                                <td data-bind="text:detail.url"></td>
                                <td data-bind="text:detail.type"></td>
                                <td data-bind="text:detail.author"></td>
                                <td data-bind="text:detail.authorId"></td>
                                <td data-bind="text:detail.postTime"></td>
                                <td data-bind="text:detail.views"></td>
                                <td data-bind="text:detail.replys"></td>
                                <td data-bind="text:detail.text"></td>
                                <td data-bind="text:detail.forumId"></td>
                                <td data-bind="text:detail.projectName"></td>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="12">
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
    <script type="text/javascript" src="application/js/requirejs/require.min.js"></script>
    <script type="text/javascript" src="application/js/requirejs/main.js"></script>
    <script>
        require(['common', 'app'], function (common, controller) {

            common.setCurrentInfo();

            var option = common.getDataDetailOption();
            option.dbBean = common.getRequestParam("dbBeant");
            option.taskId = common.getRequestParam("taskId");
            option.tableName = common.getRequestParam("tableName");
            controller.getTaskDetail(option);

            common.setLeftNavHeight();

            
        });
    </script>
</body>
</html>
