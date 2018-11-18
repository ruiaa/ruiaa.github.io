列出当前存储设备上的swap使用情况
swapon -s

磁盘的可用空间
df -h


创建Swap文件
根目录（/）下创建一个名叫swapfile的文件
最快捷的创建方式是fallocate命令，该命令能够创建一个预分配指定大小空间的文件。输入如下指令创建一个4GB的文件：
sudo fallocate -l 4G /swapfile



启用Swap文件
chmod 600 /swapfile
mkswap /swapfile
swapon /swapfile

Swap文件永久生效 
vi /etc/fstab
文件末尾加入
/swapfile   swap    swap    sw  0   0

检查
swapon -s

















