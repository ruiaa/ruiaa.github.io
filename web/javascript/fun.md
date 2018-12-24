#	DOM
+	选择器
document.getElementById
document.getElementsByClassName
document.getElementsByTagName

+	body
document.body;

+	属性
el.getAttribute('foo');
el.setAttribute('foo', 'bar');

data- 属性
el.getAttribute('data-foo')
el.dataset['foo']

+	CSS
el.style
el.classList.add(className);
el.classList.remove(className);
el.classList.contains(className);
el.classList.toggle(className);

+	Width & Height
// 含 scrollbar
window.document.documentElement.clientHeight;
// 不含 scrollbar
window.innerHeight;







#	
+	取整 Math

    floor向下取整:
    Math.floor(0.90); // 0

    round四舍五入
    Math.round(0.2) // 0

    ceil向上取整
    Math.ceil(0.2) // 1	

+	时间 new Date().getTime()



#	clipboard

	https://www.jsdelivr.com/package/npm/clipboard
    
    var clipboard = new ClipboardJS('#copyMail', {
        text: function() {
            return mailbox;
        }
    });
    clipboard.on('success', function(e) {
        toastr.info(null, "已复制邮箱地址到剪切板", {positionClass: "toast-center-center", timeOut: "3000"});
    });
    clipboard.on('error', function(e) {
        toastr.error(null, "复制失败，请手动选择文本并复制", {positionClass: "toast-center-center", timeOut: "3000"});
    });



#	定时

	//setInterval(code,millisec,lang)
    var int=setInterval("clock()",1000);
    window.clearInterval(int);
    
    
    //setTimeout(code,millisec,lang)
    setTimeout(function(){alert("Hello")},3000);
    
    
    




