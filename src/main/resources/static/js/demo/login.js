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