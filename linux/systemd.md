# 立即启动一个服务
$ sudo systemctl start apache.service
# 立即停止一个服务
$ sudo systemctl stop apache.service
# 重启一个服务
$ sudo systemctl restart apache.service
# 杀死一个服务的所有子进程
$ sudo systemctl kill apache.service
# 重新加载一个服务的配置文件
$ sudo systemctl reload apache.service
# 重载所有修改过的配置文件
$ sudo systemctl daemon-reload
# 显示某个 Unit 的所有底层参数
$ systemctl show httpd.service



Supervisor

# /usr/lib/systemd/system/supervisord.service
# supervisord service for systemd (CentOS 7.0+)
[Unit]
Description=Supervisor daemon

[Service]
Type=forking
ExecStart=/usr/bin/supervisord -c /etc/supervisord.conf
ExecStop=/usr/bin/supervisorctl $OPTIONS shutdown
ExecReload=/usr/bin/supervisorctl $OPTIONS reload
KillMode=process
Restart=on-failure
RestartSec=42s

[Install]
WantedBy=multi-user.target

激活开机启动命令
systemctl enable supervisord.service
启动supervisor进程
systemctl start supervisord.service
关闭supervisor进程
systemctl stop supervisord.service
如果修改了supervisor.service文件，可以通过reload命令来重新加载配置文件
systemctl reload supervisord.service











