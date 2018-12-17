$(function () {

});

function logout() {
    $.ajax({
        type: 'POST',
        url: '/logout',
        data: {},
        success: function (data) {
            if ('success' === data.code) {
                window.location.href="login.html";
            }
        }
    });
}


