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
            "comicInventory": $("#comicNum").val(),
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
                    + "<a onclick='showConsumeWin(\"" + list[i].comicCode + "\")' class='btn btn-success'>购买</a>"
                    + '</td>');
                $("#comicList tbody").append(tr);
            }
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

function showConsumeWin(comicCode) {
    $.ajax({
        type: 'POST',
        url: '/comic/getComicDetails',
        data: {
            "comicCode": comicCode
        },
        success: function (data) {
            if ('success' !== data.code) {
                return;
            }
            var price = data.data.comic.comicPrice + '（元）/ 本';
            $("#buyComicName").val(data.data.comic.comicName);
            $("#buyComicType").val(checkComicTypeText(data.data.comic.comicType));
            $("#buyComicPrice").val(price);
            $("#buyComicCode").val(data.data.comic.comicCode);
            $("#buyComicInventory").val(data.data.comic.comicInventory);
            $("#mask").show();
            $("#consumeWin").show();
        }
    });
}

function closeConsumeWin() {
    $("#consumeWin").hide();
    $("#mask").hide();
}

function toConsume() {
    $.ajax({
        type: 'POST',
        url: '/comic/buyComic',
        data: {
            "comicCode": $("#buyComicCode").val(),
            "buyComicNum": $("#buyComicNum").val()
        },
        success: function (data) {
            if ('success' !== data) {
                alert(data.data.returnMsg);
                return;
            }
            alert(data.data.returnMsg);
            closeConsumeWin();
            init();
            initIndex();
        }
    });
}


