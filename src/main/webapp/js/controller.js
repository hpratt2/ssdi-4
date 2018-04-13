var app = angular.module('index',[]);
app.controller('indexCtrl',function($scope,$filter,$http){
	$scope.sortOptions = [
		{name:'Avg. Rating', value:'-avgRating'},
		{name:'Name: A - Z', value:'name'},
		{name:'Name: Z - A', value:'-name'},
		{name:'Price: Low to High', value:'priceLow'},
		{name:'Price: High to Low', value:'-priceHigh'}
	];
	$scope.rppOptions = [25,50,100];
	$http.get('rest/winelist').then(function(response){
		$scope.status = response.status;
		$scope.wines = response.data;
		$scope.countryOptions = [];
		$scope.minRating = 100;
		$scope.maxRating = 0;
		
		for(let obj of $scope.wines){
			if(obj.country == null) continue;
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
		$scope.countryOptions.push({name:"[Unknown]",value:null});
	});
	
	$scope.currentPage = 0;
	$scope.rpp = 25;
	$scope.numPages = 1;
	
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
			}
		});
		return results;
	}
});

app.filter('countryFilter', function(){
	return function(wines, country){
		if(!angular.isDefined(country) || (country != null && country.length == 0)){
			return wines;
		}
		
		var results = [];
		angular.forEach(wines, function(wine){
			if(wine.country === country){
				results.push(wine);
			}
		});
		return results;
	}
});

app.filter('startFrom',function(){
	return function(input,start){
		if(input){
			start = +start;
			return input.slice(start);
		}
	}
})

app.filter('ceiling', function(){
	return function(value){
		if(isNaN(value)){ return 1; }
		return Math.ceil(value);
	}
});

function existsInSet(set,fieldname,value){
	return set.some(function(item){
		return item[fieldname] === value;
	});
}