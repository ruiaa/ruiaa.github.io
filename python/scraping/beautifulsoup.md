[文档](http://beautifulsoup.readthedocs.io/zh_CN/latest/#id8)
# 解析HTML文档 -> 树形结构化数据
    from bs4 import BeautifulSoup
    soup = BeautifulSoup(html_doc, 'html.parser')
    soup = BeautifulSoup(open("index.html"))
    soup = BeautifulSoup("<html>data</html>")
    
soup.title
soup.title.name
soup.title.string
soup.title.parent.name
soup.p
soup.p['class']
soup.a
soup.find_all('a')
soup.find(id="link3")
soup.get_text() # 所有文字内容

+   每个节点都是Python对象,所有对象可以归纳为4种: Tag , NavigableString , BeautifulSoup , Comment .

#   BeautifulSoup : 一个文档的全部内容
#   Tag : name,attributes

    soup = BeautifulSoup('<b class="boldest">Extremely bold</b>')
    tag = soup.b
    type(tag)
    # <class 'bs4.element.Tag'>
    
    tag.name
    tag.name = "blockquote"
    
    tag['class']
    tag.attrs
    tag['class'] = 'verybold'
    del tag['class']
    
    # 多值属性 在任何版本的HTML定义中都没有被定义为多值属性,那么Beautiful Soup会将这个属性作为字符串返回
    css_soup = BeautifulSoup('<p class="body strikeout"></p>')
    css_soup.p['class']
    # ["body", "strikeout"]
    
    id_soup = BeautifulSoup('<p id="my id"></p>')
    id_soup.p['id']
    # 'my id'


#   NavigableString  : tag.string
+   tag中包含的字符串不能编辑,但是可以被替换成其它的字符串,用 replace_with() 方法
+   如果想在Beautiful Soup之外使用 NavigableString 对象,需要调用 unicode() 方法,将该对象转换成普通的Unicode字符串,否则就算Beautiful Soup已方法已经执行结束,该对象的输出也会带有对象的引用地址.这样会浪费内存

#   注释及特殊字符串 Comment 对象是一个特殊类型的 NavigableString 对象:


#   解析器
+   Python标准库	BeautifulSoup(markup, "html.parser")
+   lxml HTML 解析器	BeautifulSoup(markup, "lxml")
+   lxml XML 解析器	    BeautifulSoup(markup, ["lxml-xml"]) BeautifulSoup(markup, "xml")
+   html5lib	BeautifulSoup(markup, "html5lib")


#   遍历文档树
##  子节点
+   soup.a  通过点取属性的方式只能获得当前名字的第一个tag
+   soup.find_all('a')  
+   tag.contents  将tag的子节点以列表的方式输出
+   tag.children 对tag的子节点进行循环 for child in title_tag.children:
+   tag.descendants 对所有tag的子孙节点进行递归循环 for child in head_tag.descendants:
+   tag.string 如果tag只有一个 NavigableString 类型子节点,那么这个tag可以使用 .string 得到子节点
+   tag.strings 如果tag中包含多个字符串 [2] ,可以使用 .strings 来循环获取:
+   tag.stripped_strings 可以去除多余空白内容 ,全部是空格的行会被忽略掉,段首和段末的空白会被删除

##  父节点
+   .parent
+   .parents 递归得到元素的所有父辈节点

##  兄弟节点
+   .next_sibling
+   .previous_sibling
+   .next_siblings 和 .previous_siblings 属性可以对当前节点的兄弟节点迭代输出


##  回退和前进
+   .next_element 属性指向解析过程中下一个被解析的对象(字符串或tag)
+   .previous_element 属性指向当前被解析的对象的前一个解析对象
+   .next_elements 和 .previous_elements 的迭代器就可以向前或向后访问文档的解析内容,就好像文档正在被解析一样

#   搜索文档树 find() find_all()
##  过滤器
+   字符串: 查找与字符串完整匹配的标签
+   正则表达式: 通过正则表达式的 match() 来匹配内容
+   列表: 返回与列表中任一元素匹配的内容
+   True: 查找到所有的tag,但是不会返回字符串节点
+   方法: 方法只接受一个元素参数 ,如果这个方法返回 True 表示当前元素匹配并且被找到,如果不是则返回 False
    
        def has_class_but_no_id(tag):
            return tag.has_attr('class') and not tag.has_attr('id')

##  find_all() 方法搜索当前tag的所有tag子节点
+   find_all( name , attrs , recursive , string , **kwargs )
+   name 参数可以查找所有名字为 name 的tag,字符串对象会被自动忽略掉
+   keyword 参数 ：如果一个指定名字的参数不是搜索内置的参数名,搜索时会把该参数当作指定名字tag的属性来搜索

        # 如果包含一个名字为 id 的参数,Beautiful Soup会搜索每个tag的”id”属性        
        soup.find_all(id='link2')
        # [<a class="sister" href="http://example.com/lacie" id="link2">Lacie</a>]
        
        
        # 如果传入 href 参数,Beautiful Soup会搜索每个tag的”href”属性
        soup.find_all(href=re.compile("elsie"))
        # [<a class="sister" href="http://example.com/elsie" id="link1">Elsie</a>]
        
        # 查找所有包含 id 属性的tag,无论 id 的值是什么
        soup.find_all(id=True)
        
        # 使用多个指定名字的参数可以同时过滤tag的多个属性
        soup.find_all(href=re.compile("elsie"), id='link1')
        
        # 有些tag属性在搜索不能使用,比如HTML5中的 data-* 属性,可以通过 find_all() 方法的 attrs 参数定义一个字典参数来搜索包含特殊属性的tag
        data_soup = BeautifulSoup('<div data-foo="value">foo!</div>')
        data_soup.find_all(data-foo="value")
        # SyntaxError: keyword can't be an expression
        
        data_soup.find_all(attrs={"data-foo": "value"})
        # [<div data-foo="value">foo!</div>]

+   按CSS搜索 : 通过 class_ 参数搜索有指定CSS类名的tag

        soup.find_all("a", class_="sister")
        soup.find_all(class_=re.compile("itl"))
        
        # 可以分别搜索tag中的每个CSS类名
        css_soup = BeautifulSoup('<p class="body strikeout"></p>')
        css_soup.find_all("p", class_="strikeout")
        # [<p class="body strikeout"></p>]
        css_soup.find_all("p", class_="body")
        # [<p class="body strikeout"></p>]
        
        # 通过CSS值完全匹配,顺序与实际不符,将搜索不到结果
        css_soup.find_all("p", class_="body strikeout")
        # [<p class="body strikeout"></p>]


+   string 参数 : 搜索文档中的字符串内容

        soup.find_all(string="Elsie")
        # [u'Elsie']
        
        soup.find_all(string=re.compile("Dormouse"))
        # [u"The Dormouse's story", u"The Dormouse's story"]
        
        soup.find_all("a", string="Elsie")
        # [<a href="http://example.com/elsie" class="sister" id="link1">Elsie</a>]

+   limit 参数 : 限制返回结果的数量
    
        soup.find_all("a", limit=2)

 +  recursive 参数   recursive=False : 只搜索tag的直接子节点


+   find_all() 一样调用tag  :  BeautifulSoup 对象和 tag 对象可以被当作一个方法来使用

        soup.find_all("a")
        soup("a")
        
        soup.title.find_all(string=True)
        soup.title(string=True)

##  find()
+   find( name , attrs , recursive , string , **kwargs )
+   find_all() 方法的返回结果是值包含一个元素的列表,而 find() 方法直接返回结果
+   find_all() 方法没有找到目标是返回空列表, find() 方法找不到目标时,返回 None .

##  find_parents() 和 find_parent()
##  find_next_siblings() 和 find_next_sibling()
##  find_previous_siblings() 和 find_previous_sibling()
##  find_all_next() 和 find_next()
##  find_all_previous() 和 find_previous()

##  CSS选择器 : .select()

#   修改文档树
##  修改tag的名称和属性
    tag.name = "blockquote"
    tag['class'] = 'verybold'
    tag['id'] = 1
    del tag['class']
    del tag['id']
##  修改 .string
    tag.string = "New link text."
    #   如果当前的tag包含了其它tag,那么给它的 .string 属性赋值会覆盖掉原有的所有内容包括子tag

##  append() : 向tag中添加内容
    oup = BeautifulSoup("<a>Foo</a>")
    soup.a.append("Bar")
    
    soup
    # <html><head></head><body><a>FooBar</a></body></html>
    soup.a.contents
    # [u'Foo', u'Bar']



#   输出
##  格式化输出 : tag.prettify() 
    print(soup.a.prettify())
    # <a href="http://example.com/">
    #  I linked to
    #  <i>
    #   example.com
    #  </i>
    # </a>
##  压缩输出 : unicode(tag) , str(tag) 
    str(soup)
    # '<html><head></head><body><a href="http://example.com/">I linked to <i>example.com</i></a></body></html>'
    
    unicode(soup.a)
    # u'<a href="http://example.com/">I linked to <i>example.com</i></a>'

str() 方法返回UTF-8编码的字符串,可以指定 编码 的设置.

还可以调用 encode() 方法获得字节码或调用 decode() 方法获得Unicode

##  输出格式
Beautiful Soup输出是会将HTML中的特殊字符转换成Unicode,比如“&lquot;”:

    soup = BeautifulSoup("&ldquo;Dammit!&rdquo; he said.")
    unicode(soup)
    # u'<html><head></head><body>\u201cDammit!\u201d he said.</body></html>'
如果将文档转换成字符串,Unicode编码会被编码成UTF-8.这样就无法正确显示HTML特殊字符了:

    str(soup)
    # '<html><head></head><body>\xe2\x80\x9cDammit!\xe2\x80\x9d he said.</body>


##  get_text() : 获取到tag中包含的所有文版内容包括子孙tag中的内容,并将结果作为Unicode字符串返回

    # 可以通过参数指定tag的文本内容的分隔符:
    soup.get_text("|")
    # u'\nI linked to |example.com|\n'
    
    # 还可以去除获得文本内容的前后空白:
    soup.get_text("|", strip=True)
    # u'I linked to|example.com'
    
    # 或者使用 .stripped_strings 生成器,获得文本列表后手动处理列表:
    [text for text in soup.stripped_strings]
    # [u'I linked to', u'example.com']


#   编码 : 指定 from_encoding="iso-8859-8"  排除 exclude_encodings=["ISO-8859-7"]
Beautiful Soup用了 编码自动检测 子库来识别当前文档编码并转换成Unicode编码. BeautifulSoup 对象的 .original_encoding 属性记录了自动识别编码的结果:

    soup.original_encoding
    'utf-8'

通过传入 from_encoding 参数来指定编码方式:

    soup = BeautifulSoup(markup, from_encoding="iso-8859-8")

##  输出编码 soup.prettify("latin-1")
