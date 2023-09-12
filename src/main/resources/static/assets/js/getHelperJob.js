$(document).ready(function () {
    let helpermemberid;

    // $.ajax({
    //     url: '/getGoogleSession',
    //     type: 'get',
    //     datatype: 'json',
    //     success: function (data) {
    //         helpermemberid = data.helperMember.helperMemberId;
    //         getJobByHelperId(helpermemberid);
    //     },
    //     error: function (error) {
    //         console.log(error)
    //     }
    // });
    $.ajax({
        url: '/getSession',
        type: 'get',
        datatype: 'json',
        success: function (data) {
            console.log(data);
            helpermemberid = data.helperMember.helperMemberId;
            getJobByHelperId(helpermemberid);
        },
        error: function (error) {
            console.log(error);
        }
    });
    function getJobByHelperId(helpermemberid) {
        $.ajax({
            url: "/getcollectids/" + helpermemberid,
            type: "GET",
            dataType: "json",
            success: function (data) {
                const tableBody = $("#helperjob");
                tableBody.empty(); // 清空表格内容以便重新填充

                data.forEach(function (item) {
                    var row = "<tr>";
                    var storeworklistid = item.storeWorkListId;
                    // 取得 storeWorkList 資料
                    row += "<td>" + item.storeName + "</td>";
                    row += "<td>" + item.changedatebegin + "</td>";
                    row += "<td>" + item.changedateeend + "</td>";
                    row += "<td> <button class='delete-btn' data-id='" + item.storeWorkListId + "'>删除</button> </td>" +
                        "</tr>";
                    tableBody.append(row);
                });

                // 绑定按钮点击事件
                tableBody.on('click', '.delete-btn', function () {
                    var jobId = $(this).data("id");
                    var rowToDelete = $(this).closest("tr");
                    $.ajax({
                        url: '/deletecollect/'+document.getElementById('helpermemberid').value+'/'+jobId,
                        type: 'delete',
                        dataType: 'text',
                        success:function(data) {
                            alert("刪除成功");
                            rowToDelete.remove();
                        },
                        error:function (error) {
                            console.error('Error:', error);
                        }
                    });
                });
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

});