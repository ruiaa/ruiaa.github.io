function ulControl() {
    var sign = document.createElement("sign");
    sign.innerHTML = "++++++";
    $("ul").before(sign);
    $("ol").before(sign);
    $("pre").before(sign);
    $("sign").click(function () {
        $(this).next().toggle();
    });
    $("ul").dblclick(function () {
        $(this).toggle();
    });
    $("ol").dblclick(function () {
        $(this).toggle();
    });
    $("pre").dblclick(function () {
        $(this).toggle();
    });
    $("sign").next().hide();
    $("sign").css("display", "block");

}
$(document).ready(function () {
    ulControl();
});