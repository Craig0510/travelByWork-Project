/**
 * 
 */
function adiminSignUp(event){
	event.preventDefault();
    const adminmember = {};

    adminmember.name = $("input[name='name']").val();
    adminmember.username = $("input[name='username']").val();
    adminmember.mobile = $("input[name='mobile']").val();
    adminmember.account = $("input[name='account']").val();
    adminmember.password = $("input[name='password']").val();
    adminmember.createtime = $("#createtime").val();
  
                 
   $.ajax({
        url: '/adminsignup',					
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


$("#adminSignUpForm").submit(adiminSignUp);