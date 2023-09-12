$(document).ready(function() {
    // 使用AJAX
    $.ajax({
        url: "/getStoreMember", 
        type: "GET",
        dataType: "json",
        success: function(data) {
           
            // 處理取來資訊
            const tableBody = $("#shareDataBody");
            tableBody.empty();

            data.forEach(function(item) {
                const row = "<tr>" +
                    "<td>" + item.storememberid + "</td>" +
                    "<td>" + item.account + "</td>" +
                    "<td>" + item.ownername + "</td>" +
                    "<td>" + item.storename + "</td>" +
                    "<td>" + item.mobile + "</td>" +
                    "<td>" + item.phone + "</td>" +
                    "<td>" + item.createtime + "</td>" +
                    "<td><button class='delete-btn' data-id='" + item.storememberid + "'>删除</button></td>" +
                    "</tr>";
                tableBody.append(row);
            });
             $(".delete-btn").click(function() {
                const jobId = $(this).data("id"); 
                deleteStoreMember(jobId);
            });
        },
    	error: function(error) { console.error('Error:', error);}
    });
});


function deleteStoreMember(jobId){

	$.ajax({
		url: "deleteStoreMemebr/"+jobId,
	    method: 'delete',
	    dataType: 'text',
	    success: result,
	    error: function(error) { console.error('Error:', error);}
	});
}

function result(data){
    if(data === "刪除成功"){
        window.location.href = "/storeFix.html";
    } else {
        alert(data);
    }
}

