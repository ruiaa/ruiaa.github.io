查询设备
--------
+   adb devices
+   adb -s serial_number command 多个设备时需要指定设备


安装应用
---------
+   adb install path_in_computer_to_apk
	+	adb install C:\Users\志瑞\Desktop\1.apk
    +   E:\androidstudio\project\linghong\canteen\app\build\outputs\apk\AndResGuard_app-release\app-release_aligned_signed.apk
+   adb uninstall path_in_computer_to_apk


wifi连接
--------
+   adb tcpip 5555 USB连接并侦听5555端口
+   adb connect device_ip_address   通过ip识别连接设备


停止 adb 服务器
--------------
+   adb kill-server

文件复制
-------
+   adb pull remote local
+   adb push local remote

解屏
-----
+   adb shell input keyevent 82 唤醒屏幕
+   adb shell input swipe 250 600 250 100 300   模拟上滑


模拟事件
-------
+   adb shell input tap x y     点击
+   adb shell input swipe x1 y1 x2 y2 t     滑动
+   adb shell input text str    输入
+   adb shell input keyevent num    按键
    +

















调试相关

// 开启adb服务
adb start-server

// 关闭adb服务
adb stop-server
// 列出所有连接的设备
adb devices

// 多个设备情况下使用 -s 参数指定对某个设备执行操作
adb -s device-id

// 设备id(device-id)可以通过 adb devices 命令看到，也可以通过如下命令获取
adb get-serialno
将system分区重新挂载为可读写分区，设备root情况下才有效

adb remount
跑 monkey 测试稳定性

adb shell monkey -v -p your.package.name 500
重启机器

// 正常重启
adb reboot

// 重启到 bootloader (刷机模式)
adb reboot bootloader

// 重启到 recovery (恢复模式)
adb reboot recovery
查看进程

// 列出进程列表 ，其中包含进程的 pid 等信息
adb shell ps

// 杀死指定pid的进程
adb shell kill pid

// 查看指定进程信息
adb shell ps -x pid
文件管理

从电脑上复制文件到设备

// 把 a.png 从电脑上拷贝到设备sd卡上
adb push ~/a.png /mnt/sdcard/

// 把 a.png 从电脑上拷贝到设备sd卡上并重命名为 b.png
adb push ~/a.png /mnt/sdcard/b.png


// 把 pic目录下所有文件从电脑上拷贝到设备sd卡上
adb push ~/pic/ /mnt/sdcard/
从设备复制文件到电脑

// 把 a.png 从设备sd卡上拷贝到电脑上
adb pull /mnt/sdcard/a.png ~/

// 把 a.png 从设备sd卡上拷贝到电脑上并命名为b.png
adb pull /mnt/sdcard/a.png ~/b.png

// 把pics目录下所有文件从设备sd卡上拷贝到电脑上
adb pull /mnt/sdcard/pics/ ~/pics/
一些基本命令

// 列出sd卡根目录下所有文件
adb shell ls /sdcard/

// 定位到 /sdcard/目录
adb shell cd /sdcard/

// 删除某文件
// -f 强制删除文件不需要确认
// -r 递归删除文件夹内文件
// -i 删除文件前需要确认
adb shell rm /sdcard/1.txt

// 创建目录
adb shell mkdir /sdcard/temp/
// 指定 -p 递归创建目录
adb shell mkdir -p  /sdcard/temp/test/abc/hello/

// 创建文件
adb shell touch /sdcard/1.txt

// 显示当前所在目录
adb shell pwd

// 拷贝文件1.txt到test目录
adb shell cp /sdcard/1.txt /sdcard/test/

// 移动文件，移动同一目录下文件相当于重命名文件
adb shell mv /sdcard/1.txt /sdcard/2.text
启动Activity等

// 启动应用
// -n 指定包名/包名+启动类的类名 ，启动类的类名必须是完整路径
adb shell am start -n package/package-activity

// 停止应用
adb shell am force-stop package

// 启动 service
adb shell am startservice [options] <INTENT>
举例：adb shell am startservice -a com.lt.test.action.ONESERVICE
举例：adb shell am startservice -n com.lt.test/.MyService

// 发送广播
adb shell am broadcast [options] <INTENT>
// 发送一个广播去关闭一个activity
adb shell am broadcast -a "action_finish"
// 恢复出厂设置的方法，会清除内存所有内容
adb shell am broadcast -a android.intent.action.MASTER_CLEAR
adb shell am broadcast -n com.lt.test/.MyBroadcast

// 列举出所有包含<INTENT>的package
adb shell pm list packages [options] <INTENT>
adb shell pm list packages com.lt
管理安装包

adb shell pm

安装应用程序

adb install xxx.apk

// 覆盖安装(保留缓存和数据)
adb install -r xxx.apk

// 安装apk到sd卡
adb install -s xxx.apk
卸载应用程序

adb uninstall package

// 卸载时保留数据和缓存目录
adb uninstall -k package
列出设备上的所有权限

adb shell pm list permissions
列出设备上安装的所有app的包名

adb shell pm list packages

// 列出指定包名对应的apk路径
adb shell pm path com.android.search

// 清空指定包名对应的应用的数据和缓存文件，开发时很有用
adb shell pm clear com.android.search
列出设备上的所有feature

adb shell pm list features
显示系统信息

// 获取系统属性，可以获取到一大堆关于系统信息（键值对形式）
adb shell getprop

// 获取系统属性并过滤出包含version的信息
adb shell getprop | grep version
查看 cpu 和 内存使用情况

// 每隔一秒会刷新一次 cpu 和 内存情况
adb shell top

// 查看占用内存前3的应用
adb shell top -m 3

// 刷新3次内存信息（不指定-n参数的话默认每秒会刷新1次数据）：
adb shell top -n 3

// 查看占用内存前3的应用，刷新1次
adb shell top -m 3 -n 1
查看系统当前 cpu 使用情况

adb shell cat /proc/cpuinfo
adb shell cat /proc/stat
查看系统当前内存使用情况

adb shell cat /proc/meminfo
查看指定包名应用内存使用情况，各项信息具体说明

adb shell dumpsys meminfo package
查看 service

adb shell service list
adb shell cat /system/build.prop
电池相关

// 查看电量管理信息，其中可以知道当前那个应用持有WAKE_LOCK锁
adb shell dumpsys power

// 查看电池用量情况
adb shell dumpsys battery

// 查看电池使用日志，图形界面日志分析参考google提供的工具 https://github.com/google/battery-historian
adb shell dumpsys batterystats
截图录屏

截图

// 截图到sd卡并命名为1.png，拷贝sd卡1.png到当前目录
adb shell screencap /sdcard/1.png
adb pull /sdcard/1.png .
录制屏幕操作（android4.4及以后支持）

// 录制屏幕操作视频到sd卡并命名为 test.mp4
// 如果想停止录制，按 Ctrl + C，否则三分钟后会自动停止录制，如果你设置了--time-limit参数将以它为准
// 拷贝sd卡 test.mp4 到点当前目录
adb shell screenrecord /sdcard/test.mp4
adb pull /sdcard/1.png .

// --size 指定录制的视频分辨率,比如设置为 800x480
// --bit-rate 设置视频采样比特率，缺省是4Mbps
// --time-limit 设置录制时间，单位为秒，默认180秒
// --verbose 录屏时电脑端屏显log,录完屏后还可以看到发出了广播 android.intent.action.MEDIA_SCANNER_SCAN_FIL

// 录制屏幕操作视频到sd卡下test1.mp4，其中录制时采样为 5Mbps，录制时间为20秒，视频分辨率设定为 1920x1080，录制时打出log
adb shell screenrecord --verbose --size 1920x1080 --bit-rate 5000000 --time-limit 20 /sdcard/test1.mp4
模拟按键

// 模拟输入，其中 %s 代表空格
adb shell input text "hello%sworld"

// 模拟按键，82 代表 KEYCODE_MENU 即菜单键
// 更多KEYCODE可以参考 http://developer.android.com/intl/zh-cn/reference/android/view/KeyEvent.html
adb shell input keyevent 82

// 模拟点击，屏幕上横坐标纵坐标分别为100 120的位置
// 要查看具体坐标值，可以打开开发者选项->指针位置
adb shell input tap 100 120

// 模拟滑动，从位置（0,1000）滑动到(800,600)
adb shell input swipe 0 1000 800 600

// 模拟长按，在位置（100,200）长按500毫秒
adb shell input swipe 100 200 100 200 500
网络相关

// 查看网络状态
adb shell netstat

// 通过 tcp/ip 连接，默认端口 5555
adb connect host:port

// 转发套接字连接
adb forward local remote
日志输出

// 查看指定 tag 日志
adb logcat -s tag

// 清除log缓存
adb logcat -c
