function sendContext(){
    var obj=new Object();
    obj.name=$('#demo-name').val();
    obj.email=$('#demo-email').val();
    obj.contactType=$('#contacttype').val();
    obj.contactDate=$('#contactdate').val();
    obj.message=$('#message').val();
    $.ajax({
        url:'/createcontactus',
        type:'post',
        datatype:'json',
        contentType:'application/json',
        data:JSON.stringify(obj),
        success:function(){alert('已收到您的來信')},
        error:function (error){console.log(error)}
    })
}

function sendCopy(){
    var obj=new Object();
    obj.name=$('#demo-name').val();
    obj.email=$('#demo-email').val();
    obj.contactType=$('#contacttype').val();
    obj.contactDate=$('#contactdate').val();
    obj.message=$('#message').val();
    $.ajax({
        url:'/sendContactUsCopy',
        type:'post',
        datatype:'json',
        contentType:'application/json',
        data:JSON.stringify(obj),
        success:function(data){alert(data)},
        error:function (error){console.log(error)}
    })
}