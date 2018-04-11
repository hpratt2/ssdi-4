$(document).ready(function(){
	$("#filter-menu-toggle").click(function(){
		if($("#filter-menu-dropdown").css('display') == 'none'){
			$("#filter-menu-dropdown").slideDown();
		}else{
			$("#filter-menu-dropdown").slideUp();
		}
	});
	$("#filter-menu-close").click(function(){
		if($("#filter-menu-dropdown").css('display') != 'none'){
			$("#filter-menu-dropdown").slideUp();
		}
	});
});