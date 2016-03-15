jQuery(function($) {
	$(window).scroll(function(){
		if ($(window).width() > 1250) {
			$("#login").stop().animate({"marginTop": ($(window).scrollTop() + 30) + "px"}, "slow" );
		}
		
		if ($(window).width() > 1533) {
			$("#shopping_cart").stop().animate({"marginTop": ($(window).scrollTop() + 30) + "px"}, "slow" );
		}
	});
});