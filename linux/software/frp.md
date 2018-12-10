#   内网穿透

##  下载 frp 并解压
wget https://github.com/fatedier/frp/releases/download/v0.9.3/frp_0.9.3_linux_amd64.tar.gz
tar -zxvf frp_0.9.3_linux_amd64.tar.gz
cd frp_0.9.3_linux_amd64

#   frpc.ini
# A:\frp\frpc -c .A:\frp\frpc.ini
type = http
local_ip = 127.0.0.1
local_port = 80
use_encryption = false
use_compression = true
custom_domains = lzrui.cn

#   frps.ini
# /usr/local/frp/frps -c /usr/local/frp/frps.ini &
[common]
vhost_http_port = 7776
bind_port = 7777
token = 12905678
dashboard_port = 7500
dashboard_user = ruiaa
dashboard_pwd = frp139162


#   开机启动项
sudo vim /etc/rc.local
添加/home/frp/frps(frps文件的绝对地址) -c /home/frp/frps.ini(同理) &
sudo chmod +x /etc/rc.d/rc.local

nohup /usr/local/frp/frps -c /usr/local/frp/frps.ini >/usr/local/frp/log.txt 2>&1 &

#   服务器端热加载配置文件
./frps -c ./frps.ini --reload




