requirejs.config({
    baseUrl: Config.BaseUrl + "js/",
    paths: {
        'jquery': 'jquery/jquery-1.11.1.min',
        'jqueryUI': 'jquery-ui/jquery-ui.min',
        'common': 'asset/common',
        'view': 'asset/view',
        'model': 'asset/model',
        'service': 'asset/service',
        'view-model': 'asset/view-model',
        'knockout': 'knockout/knockout-3.4.0',
        'testData': 'asset/testData',
    },
    shim: {
        'common': {
            deps: ['jquery']
        },
        'view': {
            deps: ['knockout']
        },
        'jqueryUI': {
            deps: ['jquery', 'css!./jquery-ui/jquery-ui.min.css']
        }
    },
    map: {
        '*': {
            'css': 'requirejs/css'
        }
    }
});