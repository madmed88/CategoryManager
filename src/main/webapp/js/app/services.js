'use strict';

angular.module('categoryService', ['ngResource']).
        factory('Category', function ($resource) {
            return $resource('rest/category/:id', {}, {
                'save': {method:'POST'},
                'get':  {method:'GET', isArray:true},
                'update': {method: 'PUT'}
            });
        });

angular.module('searchService', ['ngResource']).
	factory('SearchResult', function ($resource) {
	    return $resource('rest/search/:keyword', {}, {
	        'get':  {method:'GET', isArray:true},
	    });
	});

angular.module('breadcrumbService', ['ngResource']).
factory('Breadcrumb', function ($resource) {
    return $resource('rest/path/:id', {}, {
        'get':  {method:'GET', isArray:true},
    });
});