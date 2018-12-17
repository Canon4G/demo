$(function () {
    init();
});

function init() {
    getComicList(1);
}
function getComicList(pageNum) {
    $("#comicList tbody").empty();
    $.ajax({
        type: 'POST',
        url: '/comic/showComicList',
        data: {
            "comicName": $("#comicName").val(),
            "comicType": $("#comicType").val(),
            "pageNum": pageNum
        },
        success: function (data) {
            $("#comicList tbody").empty();
            if ('success' !== data.code) {
                return;
            }
            var list = data.data.list;
            if (0 === list.length) {
                return;
            }
            for (var i = 0; i < list.length; i++) {
                var tr = $("<tr></tr>");
                tr.html('<td>' + list[i].comicName + '</td>'
                    + '<td>' + list[i].comicPrice + '</td>'
                    + '<td>' + checkComicTypeText(list[i].comicType) + '</td>'
                    + '<td>' + list[i].comicInventory + '</td>'
                    + '<td>'
                    + "<a>购买</a>"
                    + '</td>');
                $("#comicList tbody").append(tr);
            }

            console.log(data.data.pageNum);
            console.log(data.data.pageTotal);

            // 分页
            $('#comicPage').show();
            $("#comicPage").pager({
                pagenumber: data.data.pageNum,
                pagecount: data.data.pageTotal,
                buttonClickCallback: comicPageClick
            });

        }
    });
}

function searchComic() {
    getComicList(1);
}

function checkComicTypeText(s) {
    if ('0' === s) {
        return "港漫";
    }
    if ('1' === s) {
        return "日漫";
    }
    if ('2' === s) {
        return "欧美漫画";
    }
}

function comicPageClick(page) {
    getComicList(page);
}

$("#comicSelect").on("change", function () {
    $("#comicType").val($("option:selected",this).val());
});


