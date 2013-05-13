'use strict';

function CategoryListController($scope, $http, $routeParams, $location, $route, Category, SearchResult, Breadcrumb) {
	
	//	detail route
	if ($routeParams.id) {
		$scope.categories = Category.get({id:$routeParams.id}, function (category) {});	
		$scope.breadcrumbs = Breadcrumb.get({id:$routeParams.id}, function (breadcrumb) {});	
		$scope.parentCategory = $routeParams.id;
	} 
	//	search route
	else if($routeParams.keyword){
		$scope.categories = SearchResult.get({keyword:$routeParams.keyword}, function (searchResult) {});
		$scope.parentCategory = false;
	}
	//	default route
	else {
		$scope.categories = Category.query();
		$scope.parentCategory = -1;
	}
    
	// init selectedItem
    $scope.selectedItem = 0;
    
    // open Category
    $scope.gotoCategoryChildrenPage = function () {
        $location.path("/category/"+ $scope.selectedItem);
    };
    
    // delete Category
    $scope.deleteCategory = function (id) {
        Category.remove({'id':id}, function (data) {
        	if (data.status === 'true') {
        		$route.reload();
			} else {
				alert("Category can not be removed, try to remove its children first");
			}
        });
    };
    
    // create Category
    $scope.submit = function () {
    	$scope.category.parent = $scope.parentCategory;
    	Category.save($scope.category, function (data) {
        	if (data.status === 'true') {
        		$route.reload();
			} else {
				alert("Category can not be added here");
			}
        });
    };
    
    // edit Category
    $scope.edit = function (selectedItem) {
    	$scope.category.id = selectedItem;
    	Category.update($scope.category, function (data) {
        	if (data.status === 'true') {
        		$route.reload();
			} else {
				alert("Category can not be edited with this name");
			}
        });
    };
    
    // search Category
    $scope.categorySearch = function() {
    	$location.path("/search/"+ $scope.keyword);
    };

    // client side pagination
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.numberOfPages=function(){
        return Math.ceil($scope.categories.length/$scope.pageSize);                
    };
}