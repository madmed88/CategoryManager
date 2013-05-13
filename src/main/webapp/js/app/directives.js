angular.module('jqSelectableDirective', []).directive('uiSelectable', function () {
    return {
        restrict: 'A',
        link: function (scope, iElement, iAttrs) {
        	iElement.selectable({
                stop:function(){
                	scope.selectedItem=$(".ui-selected").attr(iAttrs.uiSelectable);
                    scope.$apply();
                },
                selected: function(event, ui) {
                	$(ui.selected).siblings().removeClass("ui-selected");
                }
            });
        }
    };
});