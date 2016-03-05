define(['knockout', 'view', 'service'], function (ko, view, service) {

    var taskManager = function (option) {
        var taskList = service.getTaskManager(option);

        var taskModel = {
            taskList: ko.observableArray(taskList.data),
        };

        ko.applyBindings(taskModel);
    }

    return {
        getTaskManager: taskManager
    }
});