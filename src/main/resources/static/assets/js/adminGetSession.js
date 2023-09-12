/**
 * 
 */
$(document).ready(getSession)

function getSession() {
	$.ajax({
		url: '/getAdminSession',
		type: 'get',
		datatype: 'json',
		success:setSession,
		error: function (error) {
			console.log(error)
		}
	})
}
function setSession(data){
	document.getElementById('adminmemberid').value=data.adminmemberid;
	document.getElementById('username').value=data.username;
	document.getElementById('name').value=data.name;
	document.getElementById('account').value=data.account;
	document.getElementById('password').value=data.password;
	document.getElementById('mobile').value=data.mobile;
	document.getElementById('createtime').value=data.createtime;

}