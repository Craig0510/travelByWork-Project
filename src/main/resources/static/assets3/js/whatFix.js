$(document).ready(getSession)
$(document).ready(getAllPost)

//抓adminMember資料
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
}
//發公告
$("#addpost").submit(whatFix);
function whatFix(evt) {
    evt.preventDefault();
    const postimportant = {};
    postimportant.username= $("#username").val();
    postimportant.postwhat= $("#postwhat").val();
    postimportant.postdate= $("#postdate").val();
    postimportant.words= $("#words").val();
    postimportant.adminmemberid= $("#adminmemberid").val();
    

    $.ajax({
        url: "/posts/create", 
        type: "post",
        dataType: 'text',
        contentType: "application/json",
        data: JSON.stringify(postimportant),
        success: result, 
        error: function (myerror) { console.log(myerror) ;}
    });
}
function result(data){
    if (data === "公告新增成功") {
        window.location.href = "/whatFix"; 
    } else {
        alert(data); 
    }
}
//取得所有公告
function getAllPost(){
	$.ajax({
		url: '/posts/allpost',
		type: "GET",
        dataType: "json",
        success: function(data) {
           
            // 處理取來資訊
            const tableBody = $("#shareDataBody");
            console.log("AJAX request success!");
            tableBody.empty();

            data.forEach(function(item) {
                console.log(item); 
                const row = "<tr>" +
                    "<td>" + item.postimportantid + "</td>" +
                    "<td>" + item.adminmemberid + "</td>" +
                    "<td>" + item.username + "</td>" +
                   	"<td>" + item.postwhat + "</td>" +
                    "<td>" + item.words + "</td>" +
                    "<td>" + item.postdate + "</td>" +
                    "<td><button class='delete-btn' data-id='" + item.postimportantid + "'>删除</button></td>" +
                    "</tr>";
                tableBody.append(row);
            });

            $(".delete-btn").click(function() {
                const jobId = $(this).data("id"); 
                deletePost(jobId);
            });
        }
    });
}

function deletePost(jobId){

	$.ajax({
		url: "posts/"+jobId+"/delete",
	    method: 'delete',
	    dataType: 'text',
	    success: result,
	    error: function(error) { console.error('Error:', error);}
	});
}






