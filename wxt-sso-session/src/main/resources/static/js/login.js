function check(thisform) {

    var name=document.getElementById("name").value;  //读取表单数据，创建变量
    var pass=document.getElementById("pass").value;

    if (name==="" || pass==="") {
        alert("不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "login/login",
        data: {
            username:name,
            password:pass
        },
        success: function (msg) {
            console.log(msg);
            var code = msg.code;
            if (code === 200) {
                window.location.href = "success.html";
            }else {
                alert("登录失败，code:" + code);
            }
        }
    });
}