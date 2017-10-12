function setCenter() {
    if (window.screen.width > window.screen.height) {
        $("body").width("50%");
        $("body").css({
            position: "absolute",
            left: $(window).width()/4
        });
    }
}


$(document).ready(function () {
    setCenter();
});
