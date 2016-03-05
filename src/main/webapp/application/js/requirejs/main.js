requirejs.config({
    baseUrl: Config.BaseUrl + "js/",
    paths: {
        'jquery': 'jquery/jquery-1.11.1.min',
        'common': 'asset/common',
        'view': 'asset/view',
        'service': 'asset/service',
        'app': 'asset/app',
        'config': 'asset/config',
        'knockout': 'knockout/knockout-3.4.0',
        'kendo': 'kendo-ui/kendo.all.min',
        'knockout-kendo': 'kendo-ui/knockout-kendo.min',
        'testData': 'asset/testData',
    },
    shim: {
        'common': {
            deps: ['jquery', 'config']
        },
        'view': {
            deps: ['knockout']
        },
        'knockout-kendo': {
            deps: [
                'kendo',
                'kendo-ui/cultures/kendo.culture.zh-CN.min',
                'css!./kendo-ui/kendo.common.min.css',
                'css!./kendo-ui/kendo.default.min.css'
            ]
        }
    },
    map: {
        '*': {
            'css': 'requirejs/css'
        }
    }
});