$(document).ready(function() {
    // 使用AJAX
    $.ajax({
        url: "/getShares", 
        type: "GET",
        dataType: "json",
        success: function(data) {
            // 處理取來資訊
            const tableBody = $("#shareDataBody");
            tableBody.empty();

            data.forEach(function(item) {
                const row = "<tr>" +
                    "<td>" + item.helpershareid + "</td>" +
                    "<td>" + item.helpmemberid + "</td>" +
                    "<td>" + item.username + "</td>" +
                    "<td>" + item.account + "</td>" +
                    "<td>" + item.storename + "</td>" +
                   
                    "<td>" + item.updatedate + "</td>" +
                    "<td><button class='delete-btn' data-id='" + item.helpershareid + "'>删除</button></td>" +
                    "</tr>";
                tableBody.append(row);
            });

            // 添加刪除處理 還沒做
            $(".delete-btn").click(function() {
                const jobId = $(this).data("id");
                deleteHelperShare(jobId);
            });
        }	
    });		
});			

function deleteHelperShare(jobId){

	$.ajax({
		url: "deleteHelperShare/"+jobId,
	    method: 'delete',
	    dataType: 'text',
	    success: result,
	    error: function(error) { console.error('Error:', error);}
	});
}

function result(data){
    if(data === "刪除成功"){
        window.location.href = "/shareFix.html";
    } else {
        alert(data);
    }
}

