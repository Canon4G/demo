function toLogin() {
    $.ajax({
        type: 'POST',
        url: '/login',
        data: {
            'username' : $('#username').val(),
            'password' : $('#password').val()
        },
        success: function (data) {
            if ('success' !== data.code) {
                alert(data.data.returnMsg);
                return;
            }
            alert(data.data.returnMsg);
            window.location.href="html/demo/index.html";
        }
    });
}

function openSignWin() {
    $("#registerUser").val("");
    $("#registerPwd").val("");
    $("#registerPwd2").val("");
    $("#loginWin").hide();
    $("#signWin").show();
}

function toRegister() {
    $.ajax({
        type: 'POST',
        url: '/register',
        data: {
            "username": $("#registerUser").val(),
            "password": $("#registerPwd").val(),
            "rePassword": $("#registerPwd2").val(),
            "isAdmin": "2"
        },
        success: function (data) {
            if ('success' !== data.code) {
                alert(data.data.returnMsg);
                return;
            }
            alert(data.data.returnMsg);
            $("#signWin").hide();
            $("#loginWin").show();
        }
    });
}

function closeSignWin() {
    $("#username").val("");
    $("#password").val("");
    $("#signWin").hide();
    $("#loginWin").show();
}