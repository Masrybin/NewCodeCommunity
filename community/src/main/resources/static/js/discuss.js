function like(btn, entityType, entityId) {
    $.post(
        "/like",
        {"entityId": entityId, "entityType": entityType},
        function (data) {
            data = $.parseJSON(data);
            if (data.code == 1) {
                $(btn).children("i").text(parseInt($(btn).children("i").text()) + 1);
                $(btn).children("b").text("已赞");
            } else if (data.code == 0) {
                $(btn).children("i").text(parseInt($(btn).children("i").text()) - 1);
                $(btn).children("b").text("赞");
            } else {
                alert(data.code + ":" + data.msg);
            }
        }
    )
}