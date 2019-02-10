#   urllib
+   urllib.request.urlopen() 
urllib.request.urlopen(url, data=None, [timeout, ]*, cafile=None, capath=None, cadefault=False, context=None)
*第一个参数 String 类型的地址
data 是 bytes 类型的内容，可通过 bytes()函数转为化字节流。它也是可选参数。使用 data 参数，请求方式变成以 POST 方式提交表单。使用标准格式是application/x-www-form-urlencoded
timeout 参数是用于设置请求超时时间。单位是秒。
cafile和capath代表 CA 证书和 CA 证书的路径。如果使用HTTPS则需要用到。
context参数必须是ssl.SSLContext类型，用来指定SSL设置
cadefault参数已经被弃用，可以不用管了。
该方法也可以单独传入urllib.request.Request对象
该函数返回结果是一个http.client.HTTPResponse对象。


url = "http://tieba.baidu.com"
response = urllib.request.urlopen(url)
html = response.read()         # 获取到页面的源代码
print(html.decode('utf-8'))    # 转化为 utf-8 编码


url = "http://127.0.0.1:8000/book"
params = {
  'key':'key',
  'value':'value'
}
data = bytes(urllib.parse.urlencode(params), encoding='utf8')
response = urllib.request.urlopen(url, data=data)
print(response.read().decode('utf-8'))


+   urllib.request.Request
urllib.request.Request(url, data=None, headers={}, origin_req_host=None, unverifiable=False, method=None)
url 参数是请求链接，这个是必传参数，其他的都是可选参数。
data 参数跟 urlopen() 中的 data 参数用法相同。
headers 参数是指定发起的 HTTP 请求的头部信息。headers 是一个字典。它除了在 Request 中添加，还可以通过调用 Reques t实例的 add_header() 方法来添加请求头。
origin_req_host 参数指的是请求方的 host 名称或者 IP 地址。
unverifiable 参数表示这个请求是否是无法验证的，默认值是False。意思就是说用户没有足够权限来选择接收这个请求的结果。例如我们请求一个HTML文档中的图片，但是我们没有自动抓取图像的权限，我们就要将 unverifiable 的值设置成 True。
method 参数指的是发起的 HTTP 请求的方式，有 GET、POST、DELETE、PUT等


url = "http://tieba.baidu.com/"
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36'
}
request = urllib.request.Request(url=url, headers=headers)
response = urllib.request.urlopen(request)
print(response.read().decode('utf-8'))


+   使用代理
url = "http://tieba.baidu.com/"
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36'
}

proxy_handler = urllib.request.ProxyHandler({
    'http': 'web-proxy.oa.com:8080',
    'https': 'web-proxy.oa.com:8080'
})
opener = urllib.request.build_opener(proxy_handler)
urllib.request.install_opener(opener)

request = urllib.request.Request(url=url, headers=headers)
response = urllib.request.urlopen(request)
print(response.read().decode('utf-8'))





#   requests
r = requests.get("www.baidu.com",proxies = proxies)



#	BeautifulSoup
获取网页并解析
from bs4 import BeautifulSoup
import requests
url = 'http://python123.io/ws/demo.html'
r = requests.get(url)
soup = BeautifulSoup(r.text, "html.parser")

find_all(name, attrs, recursive, string, **kwargs) ，返回一个列表类型，存储查找的结果 **
• name：对标签名称的检索字符串
• attrs：对标签属性值的检索字符串，可标注属性检索
• recursive：是否对子孙全部检索，默认True
• string：<>…</>中字符串区域的检索字符串 

