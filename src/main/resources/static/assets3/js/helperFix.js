$(document).ready(function() {
    // 使用AJAX
    $.ajax({
        url: "/getHelperMembers", 
        type: "GET",
        dataType: "json",
        success: function(data) {
           
            // 處理取來資訊
            const tableBody = $("#shareDataBody");
            console.log("AJAX request success!");
            tableBody.empty();

            data.forEach(function(item) {
                console.log(item); // 使用item作为变量
                const row = "<tr>" +
                    "<td>" + item.helpermemberid + "</td>" +
                    "<td>" + item.account + "</td>" +
                    "<td>" + item.name + "</td>" +
                    "<td>" + item.username + "</td>" +
                    "<td>" + item.mobile + "</td>" +
                    "<td>" + item.createtime + "</td>" +
                    "<td><button class='delete-btn' data-id='" + item.helpermemberid + "'>删除</button></td>" +
                    "</tr>";
                tableBody.append(row);
            });

            // 添加刪除處理 還沒做
            $(".delete-btn").click(function() {
                const jobId = $(this).data("id"); 
                deleteHelperMemebr(jobId);
            });
        },
       
    });
});

function deleteHelperMemebr(jobId){

	$.ajax({
		url: "deleteHelperMemebr/"+jobId,
	    method: 'delete',
	    dataType: 'text',
	    success: result,
	    error: function(error) { console.error('Error:', error);}
	});
}

function result(data){
    if(data === "刪除成功"){
        window.location.href = "/helperFix.html";
    } else {
        alert(data);
    }
}

