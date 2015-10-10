//点击查询条件
var searchMenuClick = function(){
    var searchImg = $('#search-menu li:first img')[0];
    var queryContainer = $('.queryContainer');

    if (queryContainer.is(":visible")) {
        searchImg.src = searchImg.src.replace(/ico_search.gif/g, 'ico_searchup.gif');
        queryContainer.hide(200);
    } else {
        searchImg.src = searchImg.src.replace(/ico_searchup.gif/g, 'ico_search.gif');
        queryContainer.show(200);
    }
};

$(function(){
    $('#search-menu .switch').click(function() {
        searchMenuClick();
    });
});
