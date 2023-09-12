/**
 * 
 */
function storeSignUp(event){
	event.preventDefault();
    let storemember = {};
    storemember.storename = $("#storename").val();
    storemember.ownername = $("#ownername").val();
    storemember.account = $("#account").val();
    storemember.password = $("#password").val();
    storemember.email = $("#hiddenEmail").val();
    storemember.mobile = $("#mobile").val();
    storemember.phone = $("#phone").val();
    storemember.address = $("#address").val();
    storemember.createtime = $("#createtime").val();
                 
   $.ajax({
        url: 'storesignup',					
        type: 'post',
        dataType: 'text',  					
        contentType: 'application/json', 	    
        data: JSON.stringify(storemember),								
        success: result,					 
        error: function (myerror) { console.log(myerror) ;}
    });
}

function result(data){
    if (data === "新增成功") {
        window.location.href = "/emailProof"; 
    } else {
        alert(data); 
    }
}


$("#storeSignUpForm").submit(storeSignUp);