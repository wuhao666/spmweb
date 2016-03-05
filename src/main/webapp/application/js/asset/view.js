define(['knockout', 'service', 'common', 'knockout-kendo'], function (ko, service, common) {

    "use strict";

    /// <summary>
    /// 任务列表视图
    /// </summary>
    var taskManager = function (option,callFunc) {
        service.getTaskManager(option,function(dataList){
        	var model = {
                    startDate: ko.observable(""),
                    endDate: ko.observable(""),
                    searchTitle: ko.observable(""),
                    taskList: ko.observableArray(dataList.data),
                    PrePage: function () {
                        console.log("PrePage");
                    },
                    NextPage: function () {
                        console.log("NextPage");
                    },
                    FirstPage: function () {
                        console.log("FirstPage");
                    },
                    LastPage: function () {
                        console.log("LastPage");
                    },
                    GetPage: function () {
                        console.log("GetPage");
                    },
                    Search: function () {
                        var option = common.getSearchOption();
                        option.search.startDate = this.startDate;
                        option.search.endDate = this.endDate;
                        option.search.name = this.searchTitle;
                    },
                    pageSize: ko.observable(dataList.page.taskList),
                    createTask: function () {
                        common.open(Config.ServiceUrl + "lolthTask/createtaskPage.do");
                    },
                    openDetail: function (taskId, dbBean, tableName) {
                        var param = encodeURIComponent("taskId=" + taskId + "&dbBean=" + dbBean + "&tableName=" + tableName);
                        var url = Config.BaseUrl + "lolthTask/taskmanagerPage.do?" + param;
                        return url;
                    }
                };
        		callFunc(model);
        });
    };

    /// <summary>
    /// 创建任务视图
    /// </summary>
    var createTask = function (callFunc) {
    	getWebListView(function(webList){
    		var createModel = {
    	            taskName: ko.observable(""),
    	            startDate: ko.observable((new Date()).toLocaleString()),
    	            endDate: ko.observable((new Date()).toLocaleString()),
    	            webList: webList,
    	            ForumList: ko.observableArray(),
    	            DeleteForum: function () {
    	                console.log("DeleteForum");
    	            },
    	            SubmitForum: function () {
    	                console.log("DeleteForum");
    	            },
    	            AddForumFlag: ko.observable(false),
    	            AddForum: function () {
    	                this.AddForumFlag = true;

    	                var forum = {};
    	                forum.forumId = "";
    	                forum.keyword = "";

    	                this.ForumList.push(forum);
    	            },
    	            success: ko.observable("创建成功"),
    	            CreateTask: function () {
    	                var createTaskOption = {}
    	                createTaskOption.taskId = "";
    	                createTaskOption.taskName = this.taskName();
    	                createTaskOption.webId = this.webList.selected();
    	                createTaskOption.startDate = this.startDate();
    	                createTaskOption.endDate = this.endDate();
    	                createTaskOption.detailType = "频道";
    	                createTaskOption.data = this.ForumList();

    	                service.createTask();

    	                alert("保存成功");
    	                common.open(Config.BaseUrl + "page/task/taskmanager.html");
    	            }
    	        }
    		callFunc(createModel);
    	});
    }

    /// <summary>
    /// 获取平台列表任务视图
    /// </summary>
    var getWebListView = function (callFucn) {
        var dataList = service.getWebList(function(dataList){
        	var model = {
                    data: ko.observableArray(dataList),
                    selected: ko.observable(),
                    link: function (index) {
                        var url = this.data()[0]["webUrl"];
                        if (index() != undefined) {
                            url = this.data()[index() - 1]["webUrl"];
                        }
                        return url;
                    }
                };
        	
        	callFucn(model);
        });
    }

    /// <summary>
    /// 获取任务明细视图
    /// </summary>
    var taskDetail = function (option) {
        var dataList = service.getDataDetailList(option);
        var task = service.getTask(option.dbBean, option.taskId);

        var model = {
            task: {
                taskId: ko.observable(task.taskId),
                taskName: ko.observable(task.taskName),
                webName: ko.observable(task.webName),
                webModule: ko.observable(task.webModule),
                taskNums: ko.observable(task.taskNums),
                dataNums: ko.observable(task.dataNums),
                startDate: ko.observable(task.startDate),
                endDate: ko.observable(task.endDate),
                createUser: ko.observable(task.createUser),
                createDate: ko.observable(task.createDate)
            },
            detailList: ko.observableArray(dataList.data),
            columns: ko.observableArray(dataList.column),
            PrePage: function () {
                console.log("PrePage");
            },
            NextPage: function () {
                console.log("NextPage");
            },
            FirstPage: function () {
                console.log("FirstPage");
            },
            LastPage: function () {
                console.log("LastPage");
            },
            GetPage: function () {
                console.log("GetPage");
            },
            Search: function () {
            },
            pageSize: ko.observable(dataList.page.taskList),
            returnPage: function () {
                common.open(Config.BaseUrl + "/page/task/taskmanager.html");
            }
        }

        return model;
    }

    return {
        getTaskManagerView: taskManager,
        createTaskView: createTask,
        taskDetailView: taskDetail
    }
});