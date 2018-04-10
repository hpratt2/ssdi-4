var app = angular.module('index',[]);
app.controller('indexCtrl',function($scope,$http){
	$scope.minRating = 0
	$scope.maxRating = 100;
	$scope.sortOptions = [
		{name:'Avg. Rating', value:'-avgRating'},
		{name:'Name: A - Z', value:'name'},
		{name:'Name: Z - A', value:'-name'},
		{name:'Price: Low to High', value:'priceLow'},
		{name:'Price: High to Low', value:'-priceHigh'}
	];
	
	app.filter("ratingFilter", function(){
		return function(wines, minRating, maxRating){
		var wineLow = minRating;
		var wineHigh = maxRating;
		var filteredWines = [];
		
		for (var i=0; i<wines.length; i++){
			var wLow = new Wine(wines[i].minRating),
				wHigh = new Wine(wines[i].maxRating);
			if(wLow>wineLow && wHigh<maxRating){
				filteredWines.push(wines[i])
			}
		}
		
		return filteredWines;
		};	
	
	
	$http.get('rest/winelist').then(function(response){
		$scope.status = response.status;
		$scope.wines = response.data;
	});
});