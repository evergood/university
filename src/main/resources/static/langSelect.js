$(document).ready(function () {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        var queryString = window.location.search;
        var urlParams = new URLSearchParams(queryString);
        if (selectedOption !== '') {
            var newUrl;
            if (urlParams.has('lang')) {
                var reExp = /lang=\w+/;
                newUrl = queryString.replace(reExp, "lang=" + selectedOption);
            } else {
                newUrl = queryString.includes('?') ? queryString.concat("&lang=" + selectedOption) :
                    queryString.concat("?lang=" + selectedOption);
            }
            window.location.replace(newUrl);
        }
    });
});
