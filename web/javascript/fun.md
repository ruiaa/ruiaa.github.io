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
