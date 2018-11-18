https://www.cnblogs.com/zhansu/p/9231793.html

vultr的80端口？
1.查看防火墙版本号
firewall-cmd --version
2.查看防火墙状态
firewall-cmd --state
3.添加80端口的权限
firewall-cmd --zone=public --add-port=80/tcp --permanent
命令含义：
--zone #作用域
--add-port=80/tcp #添加端口，格式为：端口/通讯协议
--permanent #永久生效，没有此参数重启后失效
4.重启防火墙
systemctl restart firewalld

firewall-cmd --list-all