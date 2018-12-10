#   vsftpd
+   yum install -y vsftpd
+   vi /etc/vsftpd/vsftpd.conf
    +   anonymous_enable=NO 不允许匿名访问
    +   local_enable=YES 允许使用本地帐户进行FTP用户登录验证
    +   chroot_local_user=YES  chroot_list_enable=YES  chroot_list_file=/etc/vsftpd/chroot_list
    
        在/etc/vsftpd.chroot_list文件中列出的用户，可以切换到其他目录；未在文件中列出的用户，不能切换到其他目录。
        
    +   ascii_upload_enable=YES ascii_download_enable=YES 支持ASCII模式的上传和下载
    +   allow_writeable_chroot=YES 配置文件最后添加
+   touch /etc/vsftpd/chroot_list 创建
+   systemctl restart vsftpd.service 重启vsftpd
+   systemctl start vsftpd.service    # 启动服务
+   systemctl status vsftpd.service   # 服务状态查看
+   
+   新建FTP用户 useradd -d /var/ftp/public_root -g ftp -s /sbin/nologin ftpuser
+   修改该FTP用户密码 passwd ftpuser
+   
+   ftp:
    +   open host
    +   ls
    +   put/send LocalFile [RemoteFile]  多个 mput *.txt 
    +   get RemoteFile [LocalFile]  多个 mget *.txt 
    +   lcd 本地目录路径  !chdir 查看当前目录
    +   cd 服务器目录    dir 查看当前目录
    +   bye
    