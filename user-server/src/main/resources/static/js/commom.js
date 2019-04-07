/*$("#goods-search").click(function () {
    var key=$(this).val();
    if (!key || key == "" || key=="搜索您感兴趣的商品... "){
        layer.open({
            title: '错误'
            , content: "搜索内容不能为空！"
        });
        return;
    }
    $.ajax({
        url: "/index/searchPage/",
        data: {key: key},
        type: "get"
    });
});*/

function searchKey() {
    var key=$("#search-key").val();
    if (!key || key == "" || key=="搜索您感兴趣的商品... "){
        layer.open({
            title: '错误'
            , content: "搜索内容不能为空！"
        });
    }
    window.location.href = "/index/searchPage?key="+key;
}