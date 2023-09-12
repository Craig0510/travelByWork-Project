$(document).ready(function() {
    // 使用AJAX
    $.ajax({
        url: "/getPostJobs", 
        type: "GET",
        dataType: "json",
        success: function(data) {
           
            // 處理取來資訊
            const tableBody = $("#shareDataBody");
            tableBody.empty();

            data.forEach(function(item) {
				
				 $.ajax({
						url: '/getJobByWorkListIdSize/'+item.storeworklistid,
						type: 'get',
						datatype: 'json',
						success:function(data){
							const size=data;
							const row = "<tr>" +
		                    "<td>" + item.storeworklistid + "</td>" +
		                    "<td>" + item.storememberid + "</td>" +
		                    "<td>" + item.ownerName + "</td>" +
		                    "<td>" + item.storeName + "</td>" +
		                    "<td>" + item.location + "</td>" +
		                    "<td>" + item.mobile + "</td>" +
		                    "<td>" + size + "</td>" +
		                    "<td>" + item.workupdatetime + "</td>" +
		                    "<td><button class='delete-btn' data-id='" + item.storeworklistid + "'>删除</button></td>" +
		                    "</tr>";
		                	tableBody.append(row);
							 
						},
						error: function (error) { console.log(error); }
					});
                
            });
            $(".delete-btn").click(function() {
                const jobId = $(this).data("id");          
                deleteStoreWorkList(jobId);
            });
        },
       
    });
});

function deleteStoreWorkList(jobId){

	$.ajax({
		url: "deleteStoreWorkList/"+jobId,
	    method: 'delete',
	    dataType: 'text',
	    success: result,
	    error: function(error) { console.error('Error:', error);}
	});
}

function result(data){
    if(data === "刪除成功"){
        window.location.href = "/storeCVFix.html";
    } else {
        alert(data);
    }
}

