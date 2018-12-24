$(function () {
    init();
});

function init() {
    getConsumeList(1);
}

function getConsumeList(pageNum) {
    $("#consumeList tbody").empty();
    $.ajax({
        type: 'POST',
        url: '/consume/getConsumeList',
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
                tr.html('<td>' + list[i].consumeCode + '</td>'
                    + '<td>' + list[i].userName + '</td>'
                    + '<td>' + list[i].comicName + '</td>'
                    + '<td>' + list[i].consumeMoney + '</td>'
                    + '<td>' + list[i].productCount + '</td>'
                    + '<td>' + list[i].gmtCreate + '</td>');
                $("#consumeList tbody").append(tr);
            }
            // 分页
            $("#consumePage").show();
            $("#consumePage").pager({
                pagenumber: data.data.pageNum,
                pagecount: data.data.pageTotal,
                buttonClickCallback: consumePageClick
            });
        }
    });
}

function consumePageClick(page) {
    getConsumeList(page);
}