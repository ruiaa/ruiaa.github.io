#	net

##	TCP Socket
+	socket()创建
+	connect()连接
+	send()发送数据
+	recy()接收
+	close()

###	客户端

	# 导入socket库:
	import socket
	
	# 创建一个socket:
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	# 建立连接:
	s.connect(('www.sina.com.cn', 80))
	# 发送数据:
	s.send(b'GET / HTTP/1.1\r\nHost: www.sina.com.cn\r\nConnection: close\r\n\r\n')
	# 接收数据:
	buffer = []
	while True:
	    # 每次最多接收1k字节:
	    d = s.recv(1024)
	    if d:
	        buffer.append(d)
	    else:
	        break
	data = b''.join(buffer)
	# 关闭连接:
	s.close()

	header, html = data.split(b'\r\n\r\n', 1)
	print(header.decode('utf-8'))
	# 把接收的数据写入文件:
	with open('sina.html', 'wb') as f:
	    f.write(html)

###	服务器

    #!/usr/bin/env python3
    # -*- coding: utf-8 -*-
    
    import socket
    import threading,time
    
    # 定义线程处理每个连接
    def tcplink(sock, addr):
        print('Accept new connection from %s:%s...' % addr)
        sock.send(b'Welcome!')
        while True:
            data = sock.recv(1024)
            time.sleep(10)
            if not data or data.decode('utf-8') == 'exit':
                break
            sock.send(('Hello, %s!' % data.decode('utf-8')).encode('utf-8'))
        sock.close()
        print('Connection from %s:%s closed.' % addr)
    
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    # 监听端口:
    # 127.0.0.1本机地址 0.0.0.0绑定到所有的网络地址
    # 小于1024的端口号必须要有管理员权限才能绑定
    s.bind(('127.0.0.1', 9997))
    
    # 监听端口，传入的参数指定等待连接的最大数量
    s.listen(2)
    print('Waiting for connection...')
    
    while True:
        # 接受一个新连接:
        sock, addr = s.accept()
        # 创建新线程来处理TCP连接:
        t = threading.Thread(target=tcplink, args=(sock, addr))
        t.start()


+	测试客户端


    #!/usr/bin/env python3
    # -*- coding: utf-8 -*-
        
    import socket
    	
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # 建立连接:
    s.connect(('127.0.0.1', 9997))
    # 接收欢迎消息:
    print(s.recv(1024).decode('utf-8'))
    for data in [b'Michael', b'Tracy', b'Sarah']:
        # 发送数据:
        s.send(data)
        print(s.recv(1024).decode('utf-8'))
    s.send(b'exit')
    s.close()




##	UDP  SOCK_DGRAM

###	服务器
+	不需要调用listen()方法,直接接收来自任何客户端的数据：
+	recvfrom()返回数据和客户端的地址与端口
+	sendto()把数据用UDP发给客户端
+	服务器绑定UDP端口和TCP端口互不冲突，也就是说，UDP的9999端口与TCP的9999端口可以各自绑定。


	s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
	# 绑定端口:
	s.bind(('127.0.0.1', 9999))
	
	# 不需要调用listen()方法,直接接收来自任何客户端的数据：
	print('Bind UDP on 9999...')
	while True:
	    # 接收数据:
	    data, addr = s.recvfrom(1024)
	    print('Received from %s:%s.' % addr)
	    s.sendto(b'Hello, %s!' % data, addr)


###	客户端
+	不需要调用connect()，直接通过sendto()给服务器发数据

	s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
	for data in [b'Michael', b'Tracy', b'Sarah']:
	    # 发送数据:
	    s.sendto(data, ('127.0.0.1', 9999))
	    # 接收数据:
	    print(s.recv(1024).decode('utf-8'))
	s.close()







##	电子邮件
+	发件人 -> MUA -> MTA -> MTA -> 若干个MTA -> MDA <- MUA <- 收件人
	+	MUA ：Mail User Agent——邮件用户代理
	+	MTA ：Mail Transfer Agent——邮件传输代理
	+	MDA : Mail Delivery Agent——邮件投递代理

+	发邮件时，MUA和MTA使用的协议就是SMTP：Simple Mail Transfer Protocol
+	收邮件时，MUA和MDA使用的协议有两种：
	+	POP：Post Office Protocol，目前版本是3，俗称POP3；
	+	IMAP：Internet Message Access Protocol，目前版本是4，优点是不但能取邮件，还可以直接操作MDA上存储的邮件，比如从收件箱移到垃圾箱

###	SMTP发送邮件 email负责构造邮件，smtplib负责发送邮件

	Message
	+- MIMEBase
	   +- MIMEMultipart
	   +- MIMENonMultipart
	      +- MIMEMessage
	      +- MIMEText		'plain'，'html'
	      +- MIMEImage




    #!/usr/bin/env python3
    # -*- coding: utf-8 -*-
    
   	from email.mime.text import MIMEText
    import smtplib
    
    # 输入Email地址和口令:
    from_addr = input('From: ')
    password = input('Password: ')
    # 输入收件人地址:
    to_addr = input('To: ')
    # 输入SMTP服务器地址:
    smtp_server = input('SMTP server: ')
    
    # 构造邮件 正文，MIME的subtype，编码
    msg = MIMEText('hello, send by Python...', 'plain', 'utf-8')
    # 设置邮件的收发人
    msg['From'] = _format_addr('Python爱好者 <%s>' % from_addr)
    msg['To'] = _format_addr('管理员 <%s>' % to_addr)
    # 中文需要通过Header对象进行编码
    msg['Subject'] = Header('来自SMTP的问候……', 'utf-8').encode()
    
    
    server = smtplib.SMTP(smtp_server, 25) # SMTP协议默认端口是25
    server.set_debuglevel(1) # 打印出和SMTP服务器交互的所有信息
    server.login(from_addr, password) # 登录SMTP服务器
    # 发邮件，由于可以一次发给多个人，所以传入一个list
    # 邮件正文是一个str，as_string()把MIMEText对象变成str
    server.sendmail(from_addr, [to_addr], msg.as_string())
    server.quit()
    
    
    #
    #
    # 添加附件
    msg = MIMEMultipart()
    # 邮件正文是MIMEText:
    msg.attach(MIMEText('send with file...', 'plain', 'utf-8'))
    # 添加附件就是加上一个MIMEBase，从本地读取一个图片:
    with open('/Users/michael/Downloads/test.png', 'rb') as f:
        # 设置附件的MIME和文件名，这里是png类型:
        mime = MIMEBase('image', 'png', filename='test.png')
        # 加上必要的头信息:
        mime.add_header('Content-Disposition', 'attachment', filename='test.png')
        mime.add_header('Content-ID', '<0>')
        mime.add_header('X-Attachment-Id', '0')
        # 把附件的内容读进来:
        mime.set_payload(f.read())
        # 用Base64编码:
        encoders.encode_base64(mime)
        # 添加到MIMEMultipart:
        msg.attach(mime)
    
    
    #
    #
    # 图片嵌入到邮件正文中
    # 添加图片附件
    # 在HTML中通过引用src="cid:0"  from X-Attachment-Id
    
    
    #
    #
    # 同时支持HTML和Plain格式
    # 如果收件人无法查看HTML格式的邮件，就可以自动降级查看纯文本邮件
    msg = MIMEMultipart('alternative')
    
    msg.attach(MIMEText('hello', 'plain', 'utf-8'))
    msg.attach(MIMEText('<html><body><h1>Hello</h1></body></html>', 'html', 'utf-8'))
    
    
    #
    #
    # 加密SMTP
    # 在创建SMTP对象后，立刻调用starttls()方法
    smtp_server = 'smtp.gmail.com'
    smtp_port = 587 # Gmail的SMTP端口是587
    server = smtplib.SMTP(smtp_server, smtp_port)
    server.starttls()


###	POP3收取邮件  poplib模块

+	通过POP3下载邮件


    import poplib
    
    # 输入邮件地址, 口令和POP3服务器地址:
    email = input('Email: ')
    password = input('Password: ')
    pop3_server = input('POP3 server: ')
    
    # 连接到POP3服务器:
    server = poplib.POP3(pop3_server)
    # 可以打开或关闭调试信息:
    server.set_debuglevel(1)
    # 可选:打印POP3服务器的欢迎文字:
    print(server.getwelcome().decode('utf-8'))
    
    # 身份认证:
    server.user(email)
    server.pass_(password)
    
    # stat()返回邮件数量和占用空间:
    print('Messages: %s. Size: %s' % server.stat())
    # list()返回所有邮件的编号:
    resp, mails, octets = server.list()
    # 可以查看返回的列表类似[b'1 82923', b'2 2184', ...]
    print(mails)
    
    # 获取最新一封邮件, 注意索引号从1开始:
    index = len(mails)
    resp, lines, octets = server.retr(index)
    
    # lines存储了邮件的原始文本的每一行,
    # 可以获得整个邮件的原始文本:
    msg_content = b'\r\n'.join(lines).decode('utf-8')
    # 稍后解析出邮件:
    msg = Parser().parsestr(msg_content)
    
    # 可以根据邮件索引号直接从服务器删除邮件:
    # server.dele(index)
    # 关闭连接:
    server.quit()


+	解析邮件


    from email.parser import Parser
    from email.header import decode_header
    from email.utils import parseaddr
    
    import poplib
    
    msg = Parser().parsestr(msg_content)
    # Message对象本身可能是一个MIMEMultipart对象，
    # 即包含嵌套的其他MIMEBase对象，嵌套可能还不止一层
    
    # indent用于缩进显示:
    def print_info(msg, indent=0):
        if indent == 0:
            for header in ['From', 'To', 'Subject']:
                value = msg.get(header, '')
                if value:
                    if header=='Subject':
                        value = decode_str(value)
                    else:
                        hdr, addr = parseaddr(value)
                        name = decode_str(hdr)
                        value = u'%s <%s>' % (name, addr)
                print('%s%s: %s' % ('  ' * indent, header, value))
        if (msg.is_multipart()):
            parts = msg.get_payload()
            for n, part in enumerate(parts):
                print('%spart %s' % ('  ' * indent, n))
                print('%s--------------------' % ('  ' * indent))
                print_info(part, indent + 1)
        else:
            content_type = msg.get_content_type()
            if content_type=='text/plain' or content_type=='text/html':
                content = msg.get_payload(decode=True)
                charset = guess_charset(msg)
                if charset:
                    content = content.decode(charset)
                print('%sText: %s' % ('  ' * indent, content + '...'))
            else:
                print('%sAttachment: %s' % ('  ' * indent, content_type))
    
    
    
    #
    # 邮件的Subject或者Email中包含的名字都是经过编码后的str，要正常显示，就必须decode：
    def decode_str(s):
        value, charset = decode_header(s)[0] # decode_header()返回一个list
        if charset:
            value = value.decode(charset)
        return value
    
    
    #
    # 文本邮件的内容也是str，还需要检测编码，否则，非UTF-8编码的邮件都无法正常显示
    def guess_charset(msg):
        charset = msg.get_charset()
        if charset is None:
            content_type = msg.get('Content-Type', '').lower()
            pos = content_type.find('charset=')
            if pos >= 0:
                charset = content_type[pos + 8:].strip()
        return charset
    








































