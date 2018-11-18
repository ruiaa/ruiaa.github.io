[文档](http://docs.python-requests.org/zh_CN/latest/user/quickstart.html)
[高级特性](http://docs.python-requests.org/zh_CN/latest/user/advanced.html#advanced)

+   pip install requests
+   import requests

#   网络请求:get,post,put,delete,head,options

    r=requests.get('https://github.com/timeline.json')
    r=requests.post("http://httpbin.org/post")
    r=requests.put("http://httpbin.org/put")
    r=requests.delete("http://httpbin.org/delete")
    r=requests.head("http://httpbin.org/get") 
    r=requests.options("http://httpbin.org/get")

#   URL 参数:params={'key':'value'}

    >>> payload = {'key1': 'value1', 'key2': 'value2'}
    >>> r = requests.get("http://httpbin.org/get", params=payload)
    >>> print(r.url)
    http://httpbin.org/get?key2=value2&key1=value1


#   响应内容
+   文本 : r.text  r.encoding

        >>> import requests
        >>> r = requests.get('https://github.com/timeline.json')
        >>> r.text
        u'[{"repository":{"open_issues":0,"url":"https://github.com/...
        >>> r.encoding
        'utf-8'
        
+   二进制 : r.content

        # 自动解码 gzip 和 deflate 传输编码的响应数据
        >>> r.content
        b'[{"repository":{"open_issues":0,"url":"https://github.com/...
        
        
        >>> from PIL import Image
        >>> from io import BytesIO
        >>> i = Image.open(BytesIO(r.content))
        
+   json : r.json()

        >>> r = requests.get('https://github.com/timeline.json')
        >>> r.json()
        [{u'repository': {u'open_issues': 0, u'url': 'https://github.com/...
        
+   原始套接字 : stream=True  r.raw

        >>> r = requests.get('https://github.com/timeline.json', stream=True)
        >>> r.raw
        <requests.packages.urllib3.response.HTTPResponse object at 0x101194810>
        >>> r.raw.read(10)
        '\x1f\x8b\x08\x00\x00\x00\x00\x00\x00\x03'
        
        # 流下载保存
        with open(filename, 'wb') as fd:
            for chunk in r.iter_content(chunk_size):
                fd.write(chunk)
        

#   请求头 : headers={'key':'value'}

    >>> url = 'https://api.github.com/some/endpoint'
    >>> headers = {'user-agent': 'my-app/0.0.1'}
    
    >>> r = requests.get(url, headers=headers)


#   post 数据 : data=

+ 表单形式

        >>> payload = {'key1': 'value1', 'key2': 'value2'}
        
        >>> r = requests.post("http://httpbin.org/post", data=payload)
        >>> print(r.text)
        {
          ...
          "form": {
            "key2": "value2",
            "key1": "value1"
          },
          ...
        }


+   表单中多个元素使用同一 key 

        >>> payload = (('key1', 'value1'), ('key1', 'value2'))
        >>> r = requests.post('http://httpbin.org/post', data=payload)
        >>> print(r.text)
        {
          ...
          "form": {
            "key1": [
              "value1",
              "value2"
            ]
          },
          ...
        }

+   json

        >>> import json
        >>> url = 'https://api.github.com/some/endpoint'
        >>> payload = {'some': 'data'}
        >>> r = requests.post(url, data=json.dumps(payload))
        
        >>> url = 'https://api.github.com/some/endpoint'
        >>> payload = {'some': 'data'}
        >>> r = requests.post(url, json=payload)

+   文件
    
        >>> url = 'http://httpbin.org/post'
        >>> files = {'file': open('report.xls', 'rb')}
        >>> r = requests.post(url, files=files)
        >>> r.text
        {
          ...
          "files": {
            "file": "<censored...binary...data>"
          },
          ...
        }
        
        
        # 流式上传
        with open('content_with_size') as buf:
            requests.post('url',data=buf)
            

#   响应状态码 : r.status_code


#   响应头 : r.headers


#   Cookie : r.cookies  cookies=

    >>> url = 'http://example.com/some/cookie/setting/url'
    >>> r = requests.get(url)
    >>> r.cookies['example_cookie_name']
    'example_cookie_value'
    
    >>> url = 'http://httpbin.org/cookies'
    >>> cookies = dict(cookies_are='working')
    >>> r = requests.get(url, cookies=cookies)
    >>> r.text
    '{"cookies": {"cookies_are": "working"}}'



#   重定向与请求历史 : r.history
+   禁用重定向 allow_redirects=False



#   超时 timeout=second
+   timeout 仅对连接过程有效，与响应体的下载无关


#   错误与异常

遇到网络问题（如：DNS 查询失败、拒绝连接等）时，Requests 会抛出一个 ConnectionError 异常。

如果 HTTP 请求返回了不成功的状态码， Response.raise_for_status() 会抛出一个 HTTPError 异常。

若请求超时，则抛出一个 Timeout 异常。

若请求超过了设定的最大重定向次数，则会抛出一个 TooManyRedirects 异常。

所有Requests显式抛出的异常都继承自 requests.exceptions.RequestException 。



#   SSL证书校验 : verify = False，跳过验证SSL

#   代理 : proxies
    
    proxies={
      'https':'http://127.0.0.1:1080'
    }
    r = requests.post('url', proxies=proxies)
    
    # 全局代理
    export HTTP_PROXY="http://127.0.0.1:8880"
    export HTTPS_PROXY="http://127.0.0.1:9990"





