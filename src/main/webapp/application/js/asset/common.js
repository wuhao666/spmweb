define(['jquery'], function ($) {
    var setCurrentInfo = function () {
        $(".account").text(getConfig("currentUser"));
        $(".logout a").attr("href", getConfig("logoutUrl"));
    }
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

    var getConfig = function (key) {
        return Config[key];
    }

    return {
        setLeftNavHeight: setLeftNavHeight,
        setCurrentInfo: setCurrentInfo,
        getConfig: getConfig
    }
});