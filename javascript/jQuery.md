#   jQuery
+   由于 jQuery 是为处理 HTML 事件而特别设计的，那么当您遵循以下原则时，您的代码会更恰当且更易维护：
    +   把所有 jQuery 代码置于事件处理函数中
    +   把所有事件处理函数置于文档就绪事件处理器中
    +   把 jQuery 代码置于单独的 .js 文件中
    +   如果存在名称冲突，则重命名 jQuery 库(var jq=jQuery.noConflict()，jq代替$符号)


+   基础语法：$(selector).action()
+   $() ：传递DOM对象，返回jQuery对象，允许通过CSS选择器来选取元素
+   元素选取$()：
    +   CSS 选择器
    +   $("p")      标签。选取 <p> 元素。
    +   $("p.intro")标签+class。选取所有 class="intro" 的 <p> 元素。
    +   $("p#demo") 标签+id。选取所有 id="demo" 的 <p> 元素。
    +   
    +   XPath 表达式
    +   $("[href]") 选取所有带有 href 属性的元素。
    +   $("[href='#']") 选取所有带有 href 值等于 "#" 的元素。
    +   $("[href!='#']") 选取所有带有 href 值不等于 "#" 的元素。
    +   $("[href$='.jpg']") 选取所有 href 值以 ".jpg" 结尾的元素
+   遍历
    +   向上遍历 
        +   parent()  被选元素的直接父元素
        +   parents() 被选元素的所有祖先元素，它一路向上直到文档的根元素 (<html>)，可以使用可选参数来过滤对祖先元素的搜索。
        +   parentsUntil("toWhere") 返回介于两个给定元素之间的所有祖先元素。
    +   向下遍历
        +   children() 返回被选元素的所有直接子元素
        +   find("toWhere") 返回被选元素的后代元素，一路向下直到最后一个后代。
    +   水平遍历
        +   siblings()  被选元素的所有同胞元素。
        +   next()  被选元素的下一个同胞元素
        +   nextAll()  被选元素的所有跟随的同胞元素
        +   nextUntil()  返回介于两个给定参数之间的所有跟随的同胞元素。
        +   prev()
        +   prevAll()
        +   prevUntil()
    +   过滤
        +   first() 方法返回被选元素的首个元素
        +   last() 方法返回被选元素的最后一个元素。
        +   eq() 方法返回被选元素中带有指定索引号的元素
        +   filter() 方法规定标准。不匹配这个标准的元素会被从集合中删除，匹配的元素会被返回。
        +   not() 方法返回不匹配标准的所有元素。
+   元素(jQuery对象)操作 $("selector").action();
    +   css("attribute","value")
    +   显示/隐藏
        +   hide(speed,callback);
        +   show(speed,function(){...});
        +   toggle();切换hide，show
    +   淡入淡出
        +   fadeIn(speed,callback)
        +   fadeOut()
        +   fadeToggle()
        +   fadeTo(speed,opacity,callback)
    +   滑动
        +   slideDown(speed,callback)
        +   slideUp()
        +   slideToggle()
    +   动画
        +   animate({params},speed,callback);
        
                  $("div").animate({
                    left:'250px',
                    height:'+=150px',
                    width:'+=150px'
                  });
                  
                  顺序执行
                  div.animate({height:'300px',opacity:'0.4'},"slow");
                  div.animate({width:'300px',opacity:'0.8'},"slow");
                  div.animate({height:'100px',opacity:'0.4'},"slow");
                  div.animate({width:'100px',opacity:'0.8'},"slow");
                  
                  使用 Camel 标记法书写所有的属性名，比如，必须使用 paddingLeft 而不是 padding-left
                  如需对位置进行操作，要记得首先把元素的 CSS position 属性设置为 relative、fixed 或 absolute！
        
        +   stop(stopAll,goToEnd);
            +   stopAll 参数规定是否应该清除动画队列。默认是 false，即仅停止活动的动画，允许任何排入队列的动画向后执行。
            +   goToEnd 参数规定是否立即完成当前动画。默认是 false。     
+   事件 $("selector").event(function(){...});
    +   ready
    +   click
    +   dblclick
    +   focus
    +   mouseover
    
+   DOM 操作 $("selector").field("value");
    +   获取和设置元素内容属性
        +   text("newvalue") - 设置或返回所选元素的文本内容
        +   html("newvalue") - 设置或返回所选元素的内容（包括子元素的HTML标记）
        +   val("newvalue") - 设置或返回表单字段的值
        +   attr("attrname") 获取属性值
        
                //text()、html() 以及 val() 的回调函数
                $("#test1").text(function(i,origText){
                    return "Old text: " + origText + " New text: Hello world!
                    (index: " + i + ")";
                  });
                  
                //设置属性 - attr()
                $("#w3s").attr("href","http://www.w3school.com.cn/jquery");
                $("#w3s").attr({
                    "href" : "http://www.w3school.com.cn/jquery",
                    "title" : "W3School jQuery Tutorial"
                  });
                $("#w3s").attr("href", function(i,origValue){
                  return origValue + "/jquery";
                });
    +   添加内容/元素
        +   append("tex") - 在被选元素的结尾插入内容
        +   prepend("<p>text</p>") - 在被选元素的开头插入元素
        +   after() - 在被选元素之后插入内容
        +   before() - 在被选元素之前插入内容
    +   删除
        +   remove() - 删除被选元素（及其子元素）
        +   empty() - 从被选元素中删除子元素
        +   remove(".italic") 对被删元素进行过滤
    +   CSS
        +   addClass("class1 class2") - 向被选元素添加一个或多个类
        +   removeClass() - 从被选元素删除一个或多个类
        +   toggleClass() - 对被选元素进行添加/删除类的切换操作
        +   css("propertyname"); 获取样式属性
        +   css("propertyname","value"); 指定的 CSS 属性
    +   尺寸
        +   width()  不包括内边距、边框或外边距
        +   height() 
        +   innerWidth()  包括内边距
        +   innerHeight()
        +   outerWidth()  包括内边距、边框和外边距
        +   outerHeight()


#   jQuery - AJAX
+   $.ajax() 方法通过 HTTP 请求加载远程数据。该方法是 jQuery 底层 AJAX 实现

        //jQuery.ajax([settings])
        //通过 $.ajaxSetup() 设置任何选项的默认值
        $.ajax({ 
                 async: true,           //异步请求
                 contentType: "application/x-www-form-urlencoded",     //发送信息至服务器时内容编码类型
                 url: "test.html", 
                 context: document.body,            //回调函数的上下文,回调函数内this指向(如果不设定,则指向options)
                 success: function(){
                    $(this).addClass("done");
                 }
                });


+   回调函数
        如果要处理 $.ajax() 得到的数据，则需要使用回调函数：beforeSend、error、dataFilter、success、complete。
        beforeSend
        在发送请求之前调用，并且传入一个 XMLHttpRequest 作为参数。
        error
        在请求出错时调用。传入 XMLHttpRequest 对象，描述错误类型的字符串以及一个异常对象（如果有的话）
        dataFilter
        在请求成功之后调用。传入返回的数据以及 "dataType" 参数的值。并且必须返回新的数据（可能是处理过的）传递给 success 回调函数。
        success
        当请求之后调用。传入返回后的数据，以及包含成功代码的字符串。
        complete
        当请求完成之后调用这个函数，无论成功或失败。传入 XMLHttpRequest 对象，以及一个包含成功或错误代码的字符串。
        
+   $.get(URL,callback);
+   $.post(URL,data,callback);

        $.get("demo_test.asp",function(data,status){
          alert("Data: " + data + "\nStatus: " + status);
        });
        
        $.post("demo_test_post.asp",
        {
          name:"Donald Duck",
          city:"Duckburg"
        },
        function(data,status){
          alert("Data: " + data + "\nStatus: " + status);
        });






















