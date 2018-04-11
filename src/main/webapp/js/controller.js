var app = angular.module('index',[]);
app.controller('indexCtrl',function($scope,$http){
	$scope.sortOptions = [
		{name:'Avg. Rating', value:'-avgRating'},
		{name:'Name: A - Z', value:'name'},
		{name:'Name: Z - A', value:'-name'},
		{name:'Price: Low to High', value:'priceLow'},
		{name:'Price: High to Low', value:'-priceHigh'}
	];
	$http.get('rest/winelist').then(function(response){
		$scope.status = response.status;
		$scope.wines = response.data;
		$scope.countryOptions = [];
		$scope.minRating = 100;
		$scope.maxRating = 0;
		
		for(let obj of $scope.wines){
			if(!existsInSet($scope.countryOptions,'name',obj.country)){
				$scope.countryOptions.push({name:obj.country,value:obj.country});
			}
			if(obj.avgRating < $scope.minRating){ $scope.minRating = obj.avgRating; }
			if(obj.avgRating > $scope.maxRating){ $scope.maxRating = obj.avgRating; }
		}
		
		$scope.countryOptions.sort(function(a,b){
			if(a.name < b.name) return -1;
			else if(a.name > b.name) return 1;
			else return 0;
		});
		$scope.countryOptions.unshift({name:"Any",value:""});
	});
});

app.filter("ratingFilter", function($filter){
	return function(wines, minRating, maxRating){
		if(!angular.isDefined(minRating) || !angular.isDefined(maxRating)){
			return wines;
		}
		
		var results = [];
		angular.forEach(wines, function(wine){
			if(wine.avgRating >= minRating && wine.avgRating <= maxRating){
				results.push(wine);
			};
		});
		return results;
	}
});

function existsInSet(set,fieldname,value){
	return set.some(function(item){
		return item[fieldname] === value;
	});
}