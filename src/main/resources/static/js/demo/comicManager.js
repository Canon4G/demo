$(function () {
    init();
});

function init() {
    showComicList(1);
}


function showComicList(pageNum) {
    $("#comicManagerList tbody").empty();
    $.ajax({
        type: 'POST',
        url: '/comic/showComicList',
        data: {
            "comicName": $("#comicNameManager").val(),
            "comicType": $("#comicTypeManager").val(),
            "comicInventory": $("#comicNumManager").val(),
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
                tr.html('<td>' + list[i].comicName + '</td>'
                    + '<td>' + list[i].comicPrice + '</td>'
                    + '<td>' + checkComicTypeText(list[i].comicType) + '</td>'
                    + '<td>' + list[i].comicInventory + '</td>'
                    + '<td>'
                    + " &nbsp;&nbsp;<a onclick='showUpdateComicWin(\"" + list[i].comicCode + "\")' class='btn btn-warning'>修改</a>"
                    + " &nbsp;&nbsp;<a onclick='toDeleteComic(\"" + list[i].comicCode + "\")' class='btn btn-danger'>删除</a>"
                    + '</td>');
                $("#comicManagerList tbody").append(tr);
            }
            // 分页
            $("#comicManagerPage").show();
            $("#comicManagerPage").pager({
                pagenumber: data.data.pageNum,
                pagecount: data.data.pageTotal,
                buttonClickCallback: comicManagerPageClick
            });
        }
    });

}

function comicManagerPageClick(page) {
    showComicList(page);
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

function searchComicManager() {
    showComicList(1);
}

/**
 * 打开商品添加窗口
 */
function showAddComicWin() {
    $("#addComicName").val("");
    $("#addComicPrice").val("");
    $("#addComicInventory").val("");
    $("#mask").show();
    $("#addComicWin").show();
}

/**
 * 确认添加商品
 */
function toAddComic() {
    $.ajax({
        type: 'POST',
        url: '',
        data: {
            "comicName": $("#addComicName").val(),
            "comicPrice": $("#addComicPrice").val(),
            "comicInventory": $("#addComicInventory").val(),
            "comicType": $("#addComicType").val()
        },
        success: function (data) {
            if ('success' !== data.code) {
                alert(data.data.returnMsg);
                return;
            }
            alert(data.data.returnMsg);
            closeAddComicWin();
            init();
        }
    });
}

/**
 * 关闭商品添加窗口
 */
function closeAddComicWin() {
    $("#addComicWin").hide();
    $("#mask").hide();
}

/**
 * 打开商品修改窗口
 */
function showUpdateComicWin(comicCode) {
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
            $("#updateComicName").val(data.data.comic.comicName);
            $("#updateComicType").val(checkComicTypeText(data.data.comic.comicType));
            $("#updateComicPrice").val(data.data.comic.comicPrice);
            $("#updateComicCode").val(data.data.comic.comicCode);
            $("#updateComicInventory").val(data.data.comic.comicInventory);
            $("#mask").show();
            $("#updateComicWin").show();
        }
    });
}

/**
 * 确认修改商品
 */
function toUpdateComic() {
    $.ajax({
        type: 'POST',
        url: '/comic/updateComic',
        data: {
            "comicName": $("#updateComicName").val(),
            "comicType": $("#updateComicType").val(),
            "comicPrice": $("#updateComicPrice").val(),
            "comicCode": $("#updateComicCode").val(),
            "comicInventory": $("#updateComicInventory").val(),
            "comicType2": $("#updateComicType2").val()
        },
        success: function (data) {
            if ('success' !== data.code) {
                alert(data.data.returnMsg);
                return;
            }
            alert(data.data.returnMsg);
            closeUpdateComicWin();
            init();
        }
    });
}

/**
 * 关闭商品修改窗口
 */
function closeUpdateComicWin() {
    $("#updateComicWin").hide();
    $("#mask").hide();
}

/**
 * 删除商品
 * @param comicCode 商品编码
 */
function toDeleteComic(comicCode) {
    var r = confirm("确定要删除么?");
    if (!r) {
        init();
    } else {
        $.ajax({
            type: 'POST',
            url: '/comic/deleteComic',
            data: {"comicCode": comicCode},
            success: function (data) {
                if ('success' !== data.code) {
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
$("#comicSelectManager").on("change", function () {
    $("#comicTypeManager").val($("option:selected",this).val());
});

/**
 * 添加下拉列表
 */
$("#addComicSelect").on("change", function () {
    $("#addComicType").val($("option:selected",this).val());
});

/**
 * 修改下拉列表
 */
$("#updateComicSelect").on("change", function () {
    $("#updateComicType2").val($("option:selected",this).val());
});




