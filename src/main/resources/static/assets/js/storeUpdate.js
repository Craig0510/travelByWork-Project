/**
 * 
 */
function storeUpdate(event){
	event.preventDefault();
	for (const input of inputs) {
		input.setAttribute('disabled', 'true');
	}
	editButton.style.display = 'inline-block';
	saveButton.style.display = 'none';
    const storemember = {};
    storemember.storememberid=$("#storememberid").val();
    storemember.storename = $("#storename").val();
    storemember.ownername = $("#ownername").val();
    storemember.account = $("#account").val();
    storemember.password = $("#password").val();
    storemember.email = $("#email").val();
    storemember.mobile = $("#mobile").val();
    storemember.phone = $("#phone").val();
    storemember.address = $("#address").val();
    storemember.createtime = $("#createtime").val();
                 
   $.ajax({
        url: 'storeupdata',					
        type: 'post',
        dataType: 'text',  					
        contentType: 'application/json', 	    
        data: JSON.stringify(storemember),								
        success: result,					 
        error: function (myerror) { console.log(myerror) ;}
    });
}

function result(data){
		alert(data);
}


$("#saveButton").click(storeUpdate);