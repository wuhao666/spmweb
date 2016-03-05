define([], function () {

    var TaskDetailModel = {
        /// <summary>
        /// 爬网任务表信息
        /// </summary>

        classBean: '',
        id: 0,
        order: '',
        tableName: '',
        tableNameCn: ''
    };

    var TaskModel = {
        /// <summary>
        /// 爬网任务详细信息
        /// </summary>

        createDate: '',
        startDate: '',
        endDate: '',
        createUser: '',
        keyword: '',
        dataNums: 0,
        detailType: '',
        remark: '',
        taskId: '',
        taskName: '',
        taskNums: '',
        taskType: '',
        webId: '',
        webModule: '',
        webName: '',
        lolthTaskDetail: []
    }

    return {
        getTaskModel: TaskModel,
        getTaskDetailModel: TaskDetailModel
    }
});