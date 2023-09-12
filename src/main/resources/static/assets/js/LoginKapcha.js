function verify() {
    var obj = new Object();
    obj.verifyCode = $("#verifycode").val();
    $.ajax({
        url: "/getLoginSession",
        type: 'get',
        contentType: 'application/json',
        success: function(data){
            var role=data.role.toString();
            if(role==="ROLE_USER"){
                $.ajax({
                    url: "/klogin",
                    type: 'post',
                    datatype: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: redirect,
                    error: function (error) {
                        console.log(error)
                    }
                })
            }else{
                setTimeout(function () {
                    $("#fail").html("")
                }, 1500);
                $("#fail").html("<h4 style='color: red'>不是小幫手會員</h4>")
            }
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function verifyStore() {
    var obj = new Object();
    obj.verifyCode = $("#verifycode").val();
    $.ajax({
        url: "/getLoginSession",
        type: 'get',
        contentType: 'application/json',
        success: function(data){
            var role=data.role.toString();
            if(role==="ROLE_ADMIN"){
                $.ajax({
                    url: "/klogin",
                    type: 'post',
                    datatype: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: redirect,
                    error: function (error) {
                        console.log(error)
                    }
                })
            }else{
                setTimeout(function () {
                    $("#fail").html("")
                }, 1500);
                $("#fail").html("<h4 style='color: red'>不是店家會員</h4>")
            }
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function redirect(data) {
    var fail = data.toString();
    if (fail === "failure") {
        setTimeout(function () {
            $("#fail").html("")
        }, 1500);
        $("#fail").html("<h4 style='color: red'>驗證碼錯誤,請檢查過後並重新輸入</h4>")
        $("#verifyImage").attr('src', 'loginconfirm?d' + new Date() * 1);
    } else {
        var result = data.role.toString();
        if (result === "ROLE_USER") {
            window.location.replace("helperAccountAfter.html")
        } else if (result === "ROLE_ADMIN") {
            window.location.replace("storeAccountAfter.html")
        }
    }
}





