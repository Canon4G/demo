$(function () {

});

function toLogin() {
    $.ajax({
        type: 'POST',
        url: '/toLogin',
        data: {
            'username' : $('#username').val(),
            'password' : $('#password').val()
        },
        success: function (data) {

        }

    });
}