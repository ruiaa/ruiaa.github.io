#   urlopen

    urllib.request.urlopen(url, data=None, [timeout, ]*, cafile=None, capath=None, cadefault=False, context=None)
    
    urlopen返回对象提供方法：
        read() , readline() ,readlines() , fileno() , close() ：对HTTPResponse类型数据进行操作
        info()：返回HTTPMessage对象，表示远程服务器返回的头信息
        getcode()：返回Http状态码。如果是http请求，200请求成功完成;404网址未找到
        geturl()：返回请求的url
        
    
    from urllib import request
    response = request.urlopen(r'http://python.org/') # <http.client.HTTPResponse object at 0x00000000048BC908> HTTPResponse类型
    page = response.read()  
    page = page.decode('utf-8')

#   Request
    
    urllib.request.Request(url, data=None, headers={}, method=None)
    
    headers = {
        'User-Agent': r'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                      r'Chrome/45.0.2454.85 Safari/537.36 115Browser/6.0.3',
        'Referer': r'http://www.lagou.com/zhaopin/Python/?labelWords=label',
        'Connection': 'keep-alive'
    }
    req = request.Request(url, headers=headers)
    page = request.urlopen(req).read()
    page = page.decode('utf-8')
    
        User-Agent ：这个头部可以携带如下几条信息：浏览器名和版本号、操作系统名和版本号、默认语言
        Referer：可以用来防止盗链，有一些网站图片显示来源http://***.com，就是检查Referer来鉴定的
        Connection：表示连接状态，记录Session的状态。


#   Post数据

    urllib.request.urlopen(url, data=None, [timeout, ]*, cafile=None, capath=None, cadefault=False, context=None)
    # data参数默认为None，当data参数不为空的时候，urlopen（）提交方式为Post。
    urllib.parse.urlencode(query, doseq=False, safe='', encoding=None, errors=None)
    
    
    data = {
        'key':'value'
    }
    data = parse.urlencode(data).encode('utf-8')
    req = request.Request(url, headers=headers, data=data)
    page = request.urlopen(req).read()
    page = page.decode('utf-8')


#   代理 

    urllib.request.ProxyHandler(proxies=None)


    proxy = request.ProxyHandler({'http': '127.0.0.1:1080'})  # 设置proxy
    opener = request.build_opener(proxy)  # 挂载opener
    request.install_opener(opener)  # 安装opener
    data = parse.urlencode(data).encode('utf-8')
    page = opener.open(url, data).read()
    page = page.decode('utf-8')

























