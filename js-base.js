function ulControl() {
    var sign = document.createElement("sign");
    sign.innerHTML = "+++";
    $("ul").before(sign);
    $("pre").before(sign);
    $("sign").click(function () {
        $(this).next().toggle();
    });
    $("sign").next().hide();
    $("sign").css("display", "block");

}
$(document).ready(function () {
    ulControl();
});