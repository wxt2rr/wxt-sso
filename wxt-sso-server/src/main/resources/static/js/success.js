$(function () {

    $.ajax({
        type: "POST",
        url: "user/info",
        data: {
        },
        success: function (msg) {
            console.log(msg);
            var code = msg.code;
            var sid = msg.sid;
            var name = msg.username;
            var time = msg.loginTime;

            console.log(code + "|" + sid);
            if (code === 200) {
                var body = $("#body");

                var html = "<h1 >登录成功！</h1>" +
                    "<h1>用户名：" + name + "</h1>" +
                    "<h1>登录时间：" + time + "</h1>" +
                    "<h1>sid：" + sid + "</h1>";
                body.html(html);
            } else {
                window.location.href = "index.html";
            }
        }
    });
})

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] === variable) {
            return pair[1];
        }
    }
    return (false);
}