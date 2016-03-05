define(['common', 'testData'], function (common) {
    var baseUrl = common.getConfig("BaseUrl");

    var serviceFactory = {
        GetTaskList: function (option) {
            var url = baseUrl + "/lolthTask/getLolthTaskList";
            return GetTaskList();
        },
        CreateTask: function () {

        },
    }


    var taskManager = function (option) {
        return serviceFactory["GetTaskList"](option);
    }

    return {
        getTaskManager: taskManager
    }
});