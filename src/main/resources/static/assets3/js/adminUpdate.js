/**
 * 
 */
const inputFields = document.querySelectorAll("input");

function adminUpdate(event){
	event.preventDefault();
	for (const input of inputFields) {
		input.setAttribute('disabled', 'true');
	}
	editButton.style.display = 'inline-block';
	saveButton.style.display = 'none';
    const adminmember = {};
    adminmember.adminmemberid=$("#adminmemberid").val();
    adminmember.name = $("#name").val();
    adminmember.username = $("#username").val();
    adminmember.mobile = $("#mobile").val();
    adminmember.account = $("#account").val();
    adminmember.password = $("#password").val();
    adminmember.createtime = $("#createtime").val();
    
                 
   $.ajax({
        url: 'adminupdate',					
        type: 'post',
        dataType: 'text',  					
        contentType: 'application/json', 	    
        data: JSON.stringify(adminmember),								
        success: result,					 
        error: function (myerror) { console.log(myerror) ;}
    });
}



function result(data){
		alert(data);
}


$("#saveButton").click(adminUpdate);
