jQuery(function($) {
	$(window).scroll(function(){
		$("#login").stop().animate({"marginTop": ($(window).scrollTop() + 30) + "px"}, "slow" );
	});
	
	$(window).scroll(function(){
		$("#shopping_cart").stop().animate({"marginTop": ($(window).scrollTop() + 30) + "px"}, "slow" );
	});
});