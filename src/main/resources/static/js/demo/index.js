$(function () {
    initIndex();
});

function initIndex() {
    $.ajax({
        type: 'POST',
        url: '/head/init',
        data: {},
        success:function (data) {
            if (null === data.code) {
                return;
            }
            if ('0' === data.data.isAdmin) {
                $("#comicPage2").show();
                $("#rechargePage").show();
                $("#consumePage").show();
            }
            $("#accountMoney span").html(data.data.accountMoney);
            $("#userName span").html(data.data.userName);
        }
    });
}


function logout() {
    var r = confirm("是否退出");
    if (!r) {
        initIndex();
    } else {
        $.ajax({
            type: 'POST',
            url: '/logout',
            data: {},
            success: function (data) {
                if ('success' === data.code) {
                    return "redirect:/skipToLogin";
                }
            }
        });
    }
}


function openRechargeWin() {
    $("#rechargeMoney").val("");
    $("#mask").show();
    $("#rechargeWin").show();
}

function closeRechargeWin() {
    $("#mask").hide();
    $("#rechargeWin").hide();
}

function toRecharge() {
    $.ajax({
        type: 'POST',
        url: '/recharge/rechargeAccount',
        data: {
            "money": $("#rechargeMoney").val(),
            "mode": $("#rechargeType").val()
        },
        success: function (data) {
            if ('success' !== data.code) {
                alert(data.data.returnMsg);
                return;
            }
            alert(data.data.returnMsg);
            $("#mask").hide();
            $("#rechargeWin").hide();
            initIndex();
        }
    });
}

$("#rechargeSelect").on("change", function () {
    $("#rechargeType").val($("option:selected",this).val());
});


