define(['knockout', 'view'], function (ko, view, service) {

    "use strict";

    /// <summary>
    /// 任务列表controller
    /// </summary>
    var taskManager = function (option) {
        view.getTaskManagerView(option,function(viewModel){
        	ko.bindingHandlers.kendoDatePicker.options = {
                    culture: 'zh-CN'
                };
                ko.options.deferUpdates = true;
                ko.applyBindings(viewModel);
        });
    }

    /// <summary>
    /// 创建任务controller
    /// </summary>
    var createTask = function () {
       view.createTaskView(function(viewModel){
        	ko.bindingHandlers.kendoDatePicker.options = {
                    culture: 'zh-CN'
                };
                ko.applyBindings(viewModel);
        });
    }

    /// <summary>
    /// 创建任务详情页controller
    /// </summary>
    var taskDetail = function (option) {
        var viewModel = view.taskDetailView(option);
        ko.applyBindings(viewModel);
    }

    return {
        getTaskManager: taskManager,
        createTask: createTask,
        getTaskDetail: taskDetail
    }
});