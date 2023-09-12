$(document).ready(function() {
let storeMember={};
	$.ajax({
		url: '/getStore',
		type: 'get',
		datatype: 'json',
		success:setSession,
		error: function (error) {
			console.log(error)
		}
	});
});

function setSession(data){
	var spanElement = document.getElementById("usernameAfterLogin");
	spanElement.textContent = " "+data.storename;
	storeMember=data;
}	