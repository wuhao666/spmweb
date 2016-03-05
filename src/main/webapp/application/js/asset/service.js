define(['common', 'testData'], function (common) {

    "use strict";

    /// <summary>
    /// 数据交互工厂
    /// </summary>
    var serviceFactory = {
        GetTaskList: function (data,callFunc) {
            var url = Config.Actions.getTaskList + data;
            return this.GetJsonData(url,null,callFunc);
        },
        CreateTask: function (data) {
            var url = Config.Actions.createTask;
            this.PostData(url, data);
        },
        GetWebList: function (callFunc) {
            var url = Config.Actions.getWebList;            
            return this.GetJsonData(url,null,callFunc);
        },
        GetDataDetailList: function () {
            var url = Config.Actions.getTaskDataList;
            return this.GetJsonData(url);
        },
        GetTask: function (data) {
            var url = Config.Actions.getTask;
            return this.GetJsonData(url,data);
        },
        GetForumList: function (data) {
            var url = Config.Actions.getForumList;
            return this.GetJsonData(url,data);
        },
        GetJsonData: function (url, data,callFunc) {
            $.getJSON(url, data, function (result) {
            	callFunc(result);
                //return result;
            });
        },
        PostData: function (url, data) {
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                success: function (data, textStatus, jqXHR) {
                },
                dataType: "json"
            });
        }
    }

    /// <summary>
    /// 获取任务列表数据
    /// </summary>
    var taskManager = function (option,callFunc) {
        serviceFactory["GetTaskList"](JSON.stringify(option),callFunc);
    }

    /// <summary>
    /// 获取平台列表数据
    /// </summary>
    var webList = function (callFunc) {
        return serviceFactory["GetWebList"](callFunc);
    }

    /// <summary>
    /// 获取任务明细数据
    /// </summary>
    var dataDetailList = function () {
        return serviceFactory["GetDataDetailList"]();
    }

    /// <summary>
    /// 获取任务数据
    /// </summary>
    var task = function (dbBean, taskId) {
        var data = {};
        data.taskId = taskId;
        data.classBean = dbBean;

        return serviceFactory["GetTask"](JSON.stringify(data));
    }

    /// <summary>
    /// 获取频道数据
    /// </summary>
    var forumList = function (option) {
        return serviceFactory["GetForumList"](JSON.stringify(option));
    }

    /// <summary>
    /// 创建新任务
    /// </summary>
    var createTask = function (option) {
        return serviceFactory["CreateTask"](JSON.stringify(option));
    }

    return {
        getTaskManager: taskManager,
        getWebList: webList,
        getDataDetailList: dataDetailList,
        getTask: task,
        getForumList: forumList,
        createTask: createTask
    }
});