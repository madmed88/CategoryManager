angular.module('paginationFilter', []).filter('startFrom', function() {
	return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    };
});

angular.module('loopFilter', []).filter('reverse', function() {
	return function(items) {
		if (typeof items !== 'undefined' && items.length > 1) {
			return items.slice().reverse();		
		} else {
			return items;
		}
	};
});