$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
    
    $('#login-button').click(function(e) {
		$(location).attr('href','login.html');
		e.preventDefault();
	});
    
    $('#verify-submit').click(function(e) {
        swal('Thanks for verifying your Account!',
        'Good Luck',
        'success');
        setTimeout(function(){
         window.location = "login.html";
        }, 3000);
        e.preventDefault();
	});



});