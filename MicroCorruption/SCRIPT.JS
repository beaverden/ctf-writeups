$("#textentry").keydown(function(evt) { 
	if (evt.which == 13) {
		setInterval(function() {
			$("#textentry").focus();
		}, 1000);	
	}
});