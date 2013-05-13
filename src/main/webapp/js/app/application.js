'use strict';

angular.module('categoryManager', ['categoryService', 'searchService', 'breadcrumbService', 'paginationFilter', 'loopFilter', 'jqSelectableDirective']).
        config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
            when('/category/list', {templateUrl:'views/category-list.html', controller:CategoryListController}).
            when('/category/:id',  {templateUrl:'views/category-list.html', controller:CategoryListController}).
            when('/search/:keyword',  {templateUrl:'views/category-list.html', controller:CategoryListController}).
            otherwise({redirectTo:'/category/list'});
}]);