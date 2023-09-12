$(document).ready(function() {
let helperMember={};
	$.ajax({
		url: '/getGoogleSession',
		type: 'get',
		datatype: 'json',
		success:setSession,
		error: function (error) {
			console.log(error)
		}
	});
	$.ajax({
		url: '/getSession',
		type: 'get',
		datatype: 'json',
		success:setSession,
		error: function (error) { console.log(error); }
	});
});

function setSession(data){
	var spanElement = document.getElementById("usernameAfterLogin");
	spanElement.textContent = " "+data.helperMember.username;
	helperMember=data;
}	