jQuery(function($) {
	$(window).scroll(function(){
		$("#login").stop().animate({"marginTop": ($(window).scrollTop()) + "px"}, "slow" );
	});
});