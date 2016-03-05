Config = window.Config || {};

/// <summary>
/// 数据交互接口
/// </summary>
Config.Actions = Config.Actions || {
    //用户注销
    logout: Config.ServiceUrl + "logout.do",
    //获取任务列表
    getTaskList: Config.ServiceUrl + "lolthTask/getLolthTaskList/",
    //新建爬虫任务
    createTask: Config.ServiceUrl + "lolthTask/startLolthTask/",
    //获取爬网数据明细
    getTaskDataList: Config.ServiceUrl + "lolthData/getLolthData.do",
    //获取任务
    getTask: Config.ServiceUrl + "lolthTask/getLolthTaskDetail.do",
    //获取平台列表
    getWebList: Config.ServiceUrl + "webContrast/getWebContratList.do",
    //获取频道列表
    getForumList: Config.ServiceUrl + "forumData/getForumDataList/"
};