$(document).ready(function() {
    // 當搜尋按鈕被點擊時觸發
    $("#searchButton").click(function() {
        var searchText = $("#searchInput").val(); // 獲取搜尋輸入的文本
        searchText = searchText.toLowerCase(); // 將搜尋文本轉換為小寫，以進行不區分大小寫的比較

        // 遍歷每一行，隱藏不匹配的行並顯示匹配的行
        $("#shareDataBody tr").each(function() {
            var rowText = $(this).text().toLowerCase(); // 獲取行的文本
            if (rowText.includes(searchText)) {
                $(this).show(); // 顯示匹配的行
            } else {
                $(this).hide(); // 隱藏不匹配的行
            }
        });
    });
});