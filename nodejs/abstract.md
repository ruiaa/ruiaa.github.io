//引入 required 模块
var http = require("http");

//创建服务器
http.createServer(function (request, response) {
    // 发送 HTTP 头部 
    response.writeHead(200, {'Content-Type': 'text/plain'});
    // 发送响应数据 "Hello World"
    response.end('Hello World\n');
}).listen(8888);

//接收请求与响应请求
