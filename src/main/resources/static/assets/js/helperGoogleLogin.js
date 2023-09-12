/**
 * 
 */
function onSignIn(response) {
		var credential = response.credential,
			profile = JSON.parse(decodeURIComponent(escape(window.atob(credential.split(".")[1].replace(/-/g, "+").replace(/_/g, "/"))))) 
		sendBackend(profile);
	}
	function sendBackend(profile){
		var obj=new Object();
		obj.account=profile.email;
		obj.name=profile.name;
		obj.email=profile.email;
		obj.country=profile.locale;
		obj.createtime=new Date();
		$.ajax({
			url:"/googleLogin",
			type:"post",
			contentType:"application/json",
			datatype:"text",
			data:JSON.stringify(obj),
			success:result,
			error:function (error){
				console.log(error)
			}
		})
	}
	function result(data){
		 if (data === "成功") {
		     window.location.href = "/helperAccountAfter.html";
		 }else{
			 alert(data);
		 }
	}