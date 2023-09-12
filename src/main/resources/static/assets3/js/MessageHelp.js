$(document).ready(function() {
    // 從後端獲取訊息數據
    $.ajax({
        url: '/getAllContactUs',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            console.log(data);
            var messageData = $('#messageData');
            messageData.empty(); // 清空現有數據

            data.forEach(function(value) {
                var row = $('<tr>');
                row.append($('<td>').text(value.contactUsId));
                row.append($('<td>').text(value.email));

                // 添加 "詳細內容" 按鈕
                var contextButton = $('<button>').addClass('contextButton').text('詳細內容');
                contextButton.data('message', value); // 注意這裡是 'message' 而不是 'value'
                row.append($('<td>').append(contextButton));

                // 添加 "回信" 按鈕
                var replyButton = $('<button>').addClass('replyButton').text('回信');
                replyButton.data('reply', value); // 注意這裡是 'reply' 而不是 'value'
                row.append($('<td>').append(replyButton));

                messageData.append(row);
            });

            // 隱藏 div 元素
            $('#contextDialog, #replyDialog').hide();

            $(document).on('click', '.contextButton', function() {
                var contactUs = $(this).data('message');

                var Content = "來信者暱稱: " + contactUs.name + "<br>"+
                "信件類別: " + contactUs.contactType + "<br>"+
                "來信日期: " + contactUs.contactDate + "<br>"+
                "來信內容: " + contactUs.message ;

                $('#contextDialog').html(Content).dialog({
                    modal: true,
                    title: '詳細內容',
                    width: 500,
                    height: 400,
                    buttons: {
                        '關閉': function() {
                            $(this).dialog('close');
                        }
                    }
                });
            });

            $(document).on('click', '.replyButton', function() {
                var replyTo = $(this).data('reply');
                var Content = "收件者:" + replyTo.email + "<br/>" + 
                    "標題:打工換宿網" + replyTo.contactType + "之事宜" + "<br/>" +
                    "信件內容:親愛的" + replyTo.name + "你好，我們是打工換宿網，於" + replyTo.contactDate + "收到您有關於" + replyTo.contactType + "的來信！" + "<br/>" +
                    "您所傳送的內容如下：" + replyTo.message + "（以下就是我們回信的內容）" + "<br/>";
                var additionalTextArea = '<textarea id="additionalText" rows="4" cols="50"></textarea>';
                Content += additionalTextArea;
                Content += "打工換宿趣誠摯感謝您，祝順心！";
                $('#contextDialog').html(Content).dialog({
                    modal: true,
                    title: '回信',
                    width: 500,
                    height: 400,
                    buttons: {
                        '關閉': function() {
                            $(this).dialog('close');
                        }, '回信': function() {
                            var obj = new Object();
                            var contextArea = $("#additionalText").val();
                            obj.toEmail = replyTo.email;
                            obj.title = "打工換宿網" + replyTo.contactType + "之事宜";
                            obj.subject = "親愛的" + replyTo.name + "你好，我們是打工換宿趣，於" + replyTo.contactDate + "收到您有關於" + replyTo.contactType + "的來信！" + "\n" +
                                "您所傳送的內容如下：" + "\n" + replyTo.message + "\n" + "（以下就是我們回信的內容）" + "\n" + contextArea;
                            $.ajax({
                                url: '/sendReply',
                                type: 'post',
                                datatype: 'json',
                                contentType: 'application/json',
                                data: JSON.stringify(obj),
                                success: function(data) { alert("信件寄出成功"); },
                                error: function(error) { console.log(error); }
                            });
                        }
                    }
                });
            });

            // 觸發按鈕
            $("#yourButtonID").click(function() {
                // 顯示相應的 div 元素
                $("#contextDialog, #replyDialog").dialog({
                    modal: true,
                    width: 500,
                    height: 400,
                    buttons: {
                        '關閉': function() {
                            $(this).dialog('close');
                        }
                    }
                });
            });
        },
        error: function(error) {
            console.log('獲取訊息數據時出錯：', error);
        }
    });
});
