define(['jquery'], function ($) {

    "use strict";


    /// <summary>
    /// 获取登录用户信息
    /// </summary>
    var setCurrentInfo = function () {
        $(".account").text(Config.currentUser);
        $(".logout a").attr("href", Config.Actions.logout);
    }

    /// <summary>
    /// 动态计算左侧导航高度
    /// </summary>
    var setLeftNavHeight = function () {
        setHeight();

        window.onresize = function () {
            setHeight();
        }

        function setHeight() {
            var bodyHeight = $("body").height() - 65;
            var height = $(".main").height();
            $(".left-nav").height(height > bodyHeight ? height : bodyHeight);
        }

    }

    /// <summary>
    /// 创建查询任务列表搜索对象
    /// </summary>
    var getSearchTaskListOption = function () {
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

    /// <summary>
    /// 创建查询任务明细列表搜索对象
    /// </summary>
    var getSearchTaskDetailListOption = function () {
        var option = {};
        option.dbBean = '';
        option.taskId = '';
        option.tableName = '';

        option.search = {};
        option.search.text = '';
        option.search.content = '';

        option.page = {};
        option.page.pageSize = "15";
        option.page.currentPage = "1";

        return option;
    }

    var openNewPage = function (url) {
        window.location.href = url;
    }

    var getParam = function (key) {
        var url = location.search;
        url = decodeURIComponent(url);
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            var strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest[key] != undefined ? theRequest[key] : '';
    }

    return {
        setLeftNavHeight: setLeftNavHeight,
        setCurrentInfo: setCurrentInfo,
        getSearchOption: getSearchTaskListOption,
        getDataDetailOption: getSearchTaskDetailListOption,
        open: openNewPage,
        getRequestParam: getParam
    }
});