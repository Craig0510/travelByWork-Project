/**
 * 
 */
function adminLogin(event){
	event.preventDefault();
	const queryParams = {
	    account:$("#account").val(),
	   	password:$("#password").val()
	};
	const url = '/adminlogin?' + $.param(queryParams);

	$.ajax({
	    url: url,
	    method: 'get',
	    dataType: 'text',
	    xhrFields:{withCredentials:true},
	    success: result,
	    error: function(error) {
	        console.error('Error:', error);
	    }
	});
}
    
/*function result(data){
    if (data === "登入成功") {
		alert(data);
        window.location.href = "/adminAccount"; 
    } else {
        alert(data); 
    }
}*/

function result(data) {
    if (data === "登入成功") {
        alert(data);
        window.location.href = "/adminAccount2"; 
    } else {
        // 显示错误消息并设置文本颜色为红色
        const errorMessage = document.getElementById("error-message");
        errorMessage.innerText = "帳號或密碼錯誤，請重新輸入";
        errorMessage.style.color = "red";
    }
}

$("#adminLoginForm").submit(adminLogin);