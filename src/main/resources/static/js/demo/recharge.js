$(function () {
    init();
});

function init() {
    getRechargeList(1);
}

function getRechargeList(pageNum) {
    $("#rechargeList tbody").empty();
    $.ajax({
        type: 'POST',
        url: '/recharge/getRechargeList',
        data: {
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
                tr.html('<td>' + list[i].rechargeCode + '</td>'
                    +'<td>' + list[i].userName + '</td>'
                    +'<td>' + list[i].rechargeMoney + '</td>'
                    +'<td>' + getRechargeModeText(list[i].rechargeMode) + '</td>'
                    +'<td>' + list[i].gmtCreate + '</td>');
                $("#rechargeList tbody").append(tr);
            }
            // 分页
            $("#rechargePage").show();
            $("#rechargePage").pager({
                pagenumber: data.data.pageNum,
                pagecount: data.data.pageTotal,
                buttonClickCallback: rechargePageClick
            });
        }
    });
}

function rechargePageClick(page) {
    getRechargeList(page);
}

function getRechargeModeText(s) {
    if ('0' === s) {
        return "微信";
    }
    if ('1' === s) {
        return "支付宝";
    }
    if ('2' === s) {
        return "银行卡";
    }
}