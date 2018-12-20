$(function () {
    init();
});

function init() {
    getUserList(1);
}

function getUserList(pageNum) {
    $("#userManagerList tbody").empty();
    $.ajax({
        type: 'POST',
        url: '/getUserList',
        data: {
            "userName": $("#userNameManager").val(),
            "userType": $("#userTypeManager").val(),
            "pageNum": pageNum
        },
        success: function (data) {
            if ('success' !== data.code) {
                return;
            }
            var list = data.data.list;
            if (0 === list.length) {
                return;
            }
            for (var i = 0; i < list.length; i++) {
                var tr = $("<tr></tr>");
                tr.html('<td>' + list[i].id + '</td>'
                    + '<td>' + list[i].userName + '</td>'
                    + '<td>' + checkUserTypeText(list[i].isAdmin) + '</td>'
                    + '<td>' + list[i].gmtCreate + '</td>'
                    + '<td>'
                    + " &nbsp;&nbsp;<a onclick='showUpdateUserWin(\"" + list[i].userCode + "\")' class='btn btn-warning'>修改</a>"
                    + " &nbsp;&nbsp;<a onclick='toDeleteUser(\"" + list[i].userCode + "\")' class='btn btn-danger'>删除</a>"
                    + '</td>');
                $("#userManagerList tbody").append(tr);
            }
        }
    });
}

function userPageClick(page) {
    getUserList(page);
}

function checkUserTypeText(s) {
    if ('0' === s) {
        return "超级管理员";
    }
    if ('1' === s) {
        return "管理员";
    }
    if ('2' === s) {
        return "普通用户";
    }
}

function searchUserManager() {
    getUserList(1);
}

function showAddUserWin() {
    $("#addUserName").val("");
    $("#addUserPwd").val("");
    $("#addUserPwd2").val("");
    $("#mask").show();
    $("#addUserWin").show();
}

function toAddUser() {
    $.ajax({
        type: 'POST',
        url: '/register',
        data: {
            "username": $("#addUserName").val(),
            "password": $("#addUserPwd").val(),
            "rePassword": $("#addUserPwd2").val(),
            "isAdmin": $("#addUserType").val()
        },
        success: function (data) {
            if ('success' !== data.code) {
                alert(data.data.returnMsg);
                return;
            }
            alert(data.data.returnMsg);
            closeAddUserWin();
            init();
        }
    });
}

function closeAddUserWin() {
    $("#addUserWin").hide();
    $("#mask").hide();
}

function showUpdateUserWin(userCode) {
    $.ajax({
        type: 'POST',
        url: '/getUserDetails',
        data: {
            "userCode": userCode
        },
        success: function (data) {
            if ('success' !== data.code) {
                return;
            }
            $("#updateUserName").val(data.data.user.userName);
            $("#updateUserType").val(checkUserTypeText(data.data.user.isAdmin));
            $("#updateUserPwd").val("");
            $("#updateUserPwd2").val("");
            $("#updateUserPwd3").val("");
            $("#updateUserCode").val(data.data.user.userCode);
            $("#mask").show();
            $("#updateUserWin").show();
        }
    });
}

function toUpdateUser() {
    $.ajax({
        type: 'POST',
        url: '/updateUser',
        data: {
            "userCode": $("#updateUserCode").val(),
            "userName": $("#updateUserName").val(),
            "password2": $("#updateUserPwd2").val(),
            "password3": $("#updateUserPwd3").val(),
            "isAdmin": $("#updateUserType").val(),
            "isAdmin2": $("#updateUserType2").val()
        },
        success: function (data) {
            if ('success' !== data.code) {
                alert(data.data.returnMsg);
                return;
            }
            alert(data.data.returnMsg);
            closeUpdateUserWin();
            init();
        }
    });
}

function closeUpdateUserWin() {
    $("#updateUserWin").hide();
    $("#mask").hide();
}

function toDeleteUser(userCode) {
    var r = confirm("是否删除用户信息");
    if (!r) {
        init();
    } else {
        $.ajax({
            type: 'POST',
            url: '/deleteUser',
            data: {
                "userCode": userCode
            },
            success: function (data) {
                if ('success' !== data.code) {
                    alert(data.data.returnMsg);
                    return;
                }
                alert(data.data.returnMsg);
                init();
            }
        });
    }
}

/**
 * 查询下拉列表
 */
$("#userSelectManager").on("change", function () {
    $("#userTypeManager").val($("option:selected",this).val());
});

/**
 * 添加下拉列表
 */
$("#addUserSelect").on("change", function () {
    $("#addUserType").val($("option:selected",this).val());
});

/**
 * 修改下拉列表
 */
$("#updateUserSelect").on("change", function () {
    $("#updateUserType2").val($("option:selected",this).val());
});