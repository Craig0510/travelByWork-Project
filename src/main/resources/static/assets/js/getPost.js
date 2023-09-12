$(document).ready(getAllPost)
//取得所有公告
function getAllPost(){
	$.ajax({
		url: '/posts/allpost',
		type: "GET",
        dataType: "json",
        success: function(data) {
            const allpost = $("#allpost");
            data.forEach(function(item) {
				let post ='<h3>'+item.postwhat+'</h3>'
						+"<p>"+item.postdate+"</p>"
						+"<div class='col-12'>"+item.words+"</div>"+
						"<hr/>"
									
                allpost.append(post);
            });
        }
    });
}

