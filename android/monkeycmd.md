#   monkey cmd
+   [monkey 源码](http://androidxref.com/7.1.1_r6/xref/development/cmds/monkey/src/com/android/commands/monkey/)
##  常用选项：
+   --help：打印帮助信息
+   -v：指定打印信息的详细级别，一个 -v增加一个级别 ， 默认级别为 0 ,最高为-v -v -v

##  事件选项；
+   -s：指定产生随机事件种子值，相同的种子值产生相同的事件序列。如： -s 200
+   --throttle：每个事件结束后的间隔时间——降低系统的压力（如不指定，系统会尽快的发送事件序列）。如：--throttle 100
+   --throttle time --randomize-throttle 设置时间间隔随机0~time
+   --pct-touch                 0指定触摸事件的百分比，如：--pct-touch 5%
+   --pct-motion <percent>      1（滑动事件）
+   --pct-pinchzoom <percent>   2（缩放）
+   --pct-trackball <percent>   3（轨迹球事件）
+   --pct-rotation <percent>    4（屏幕旋转事件）
+   --pct-nav <percent>         5（基本导航事件 up/down/left/right）
+   --pct-majornav <percent>    6(主要导航事件 back key 、 menu key)
+   --pct-syskeys <percent>     7(系统按键事件 Home 、Back 、startCall 、 endCall 、 volumeControl)
+   --pct-appswitch <percent>   8（activity之间的切换）
+   --pct-flip <percent>        9（键盘翻转事件）
+   --pct-anyevent <percent>    10（任意事件）

##  约束选项：
+   -p：指定有效的package（如不指定，则对系统中所有package有效），一个-p 对应一个有效package， 如：-p com.ckt -p com.ckt.asura；
+   -c：activity必须至少包含一个指定的category，才能被启动，否则启动不了；

##  调试选项：
+   --dbg-no-events：初始化启动的activity，但是不产生任何事件。为了得到最佳结果，把它与-v、一个或几个包约 束、以及一个保持Monkey运行30秒或更长时间的非零值联合起来，从而提供一个环境，可以监视应用程序所调用的包之间的转换
+   --hprof：指定该项后在事件序列发送前后会立即生成分析报告 —— 一般建议指定该项。
+   --ignore-crashes：忽略崩溃
+   --ignore-timeouts：忽略ANR
+   --ignore-security-exceptions：忽略安全异常
+   --kill-process-after-error：发生错误后直接杀掉进程
+   --monitor-native-crashes：跟踪本地方法的崩溃问题
+   --wait-dbg：直到连接了调试器才执行monkey测试。

## 栗子
adb shell monkey  -p oct.mama -s 100 --throttle 10 
--ignore-crashes --ignore-timeouts --monitor-native-crashes 
--pct-appswitch 30 --pct-majornav 20 --pct-nav 20 --pct-touch 20 --pct-motion 10 
-v -v -v 10000 > D:\monkey_log.txt

#   
+   adb shell ps |grep monkey
+   adb shell kill pid
+   adb shell monkey -f <MonkeyScript> <EventCount>

#   monkey日志分析
1.  Monkey: seed=1470511671524 count=100        monkey执行的seed值和随机事件次数
2.  AllowPackage: com.UCMobile.x86              可以运行的包名
3.  // Event percentages:                       分配事件的百分比
    //   0: 15.0%
    //   1: 10.0%
    //   2: 2.0%
    //   3: 15.0%
    //   4: -0.0%
    //   5: -0.0%
    //   6: 25.0%
    //   7: 15.0%
    //   8: 2.0%
    //   9: 2.0%
    //   10: 1.0%
    //   11: 13.0%
4.  事件0：触摸事件
    Sending Touch (ACTION_DOWN): 0:(572.0,1105.0)
    Sending Touch (ACTION_UP): 0:(576.20734,1105.024)

5. 事件1：滑动事件
  Sending Touch (ACTION_DOWN): 0:(233.0,761.0)
  Sending Touch (ACTION_MOVE): 0:(208.49568,736.34766)
  Sending Touch (ACTION_MOVE): 0:(202.7063,729.8338)
  Sending Touch (ACTION_MOVE): 0:(183.89723,722.677)
  Sending Touch (ACTION_UP): 0:(174.83568,721.8229)

6.  事件2：缩放事件
  Sending Touch (ACTION_DOWN): 0:(107.0,242.0)
  Sending Touch (ACTION_POINTER_DOWN 1): 0:(108.14705,248.53061) 1:(270.0,262.0)
  Sending Touch (ACTION_MOVE): 0:(110.117355,252.96329) 1:(267.9937,262.25485)
  Sending Touch (ACTION_MOVE): 0:(111.30056,261.88846) 1:(261.90106,262.58475)
  Sending Touch (ACTION_MOVE): 0:(113.11743,265.60138) 1:(253.92662,263.13382)
  Sending Touch (ACTION_POINTER_UP 1): 0:(113.29031,267.4419) 1:(248.60628,263.23257)

7.  事件3：轨迹球事件
  Sending Trackball (ACTION_MOVE): 0:(3.0,-2.0)
  Sending Trackball (ACTION_MOVE): 0:(1.0,-1.0)

8.  事件4：屏幕旋转事件（隐藏事件）
  Sending rotation degree=0,persist=true

9.  事件5：导航事件（上下左右）
   Sending Key (ACTION_DOWN): 21    // KEYCODE_DPAD_LEF

10. 事件6：主要导航事件（menu等）
  Sending Key (ACTION_DOWN): 23    // KEYCODE_DPAD_CENTER

11. 事件7：系统按键事件(音量，home,返回按键等)
  Sending Key (ACTION_UP): 25    // KEYCODE_VOLUME_DOWN

12. 事件8：启动应用事件
  Switch: #Intent;action=android.intent.action.MAIN;category=android.intent.category.LAUNCHER;launchFlags=0x10200000;component=com.UCMobile.x86/com.UCMobile.main.UCMobile;end

13. 事件9：键盘事件（隐藏显示键盘）
  Sending Flip keyboardOpen=true

14. 事件10：其他按键
  Sending Key (ACTION_DOWN): 66    // KEYCODE_ENTER
  Sending Key (ACTION_UP): 66    // KEYCODE_ENTER

15. 延时
  Sleeping for 300 milliseconds



#   script
##  launch
+   LaunchActivity(pkg_name,launcher_activity_name)	        启动应用的Activity
+   LaunchInstrumentation(test_name,runner_name)运行一个instrumentation测试用例

##  event
### touch

Tap(x,y,tapDuration)	                    单击
PressAndHold(x,t,pressDuration)	            长按
LongPress()	                                长按两秒
Drag(sStart,yStart,xEnd,yEnd,stepCount)	    滑动
PinchZoom(pt1xStart,pt1yStart,pt1xEnd,pt1yEnd,pt2xStart,pt2yStart,pt2xEnd,pt2yEnd,stepCount)	模拟缩放手势

RotateScreen(ratationDegree,peresist)	    旋转屏幕 ratationDegree:0123分别代表0，90，180，270;peresist:两个参数^0,0旋转后固定和旋转后不固定


DispatchPointer(downtime,eventTime,action,x,yxpressure,size,metastate,xPrecision,yPrecision,device,edgeFilags)	
向指定位置发送单个手势action:0是按下，1是弹起
DispatchKey(downTime,eventTime,action,code,repeat,metaStatue,device,scancode)	发送按键消息
DispatchPress(KeyCodeName)	                按键
DispatchFlip(true/false)	                打开或关闭软键盘
DispatchString(input)	                    输入字符串
DispatchTrackball	                        轨迹球

UserWait(sleepTime)	                        让脚本中断一段时间
DeviceWakeUp()	                            唤醒屏幕
PowerLog(power_log_type,test_case_status)	模拟电池电量信息
WriteLog()	                                将电池信息写入SD卡
RunCmd()	                                运行shell命令



##  shell
RunCmd("...")	                            运行shell命令
screencap -p /data/local/tmp/tmp.png        截屏      shell screencap -p /sdcard/aaa/"%date:~0,10% %TIME%.png"

ProfileWait	                                等待5秒
StartCaptureFramerate()	                    获取帧率
EndCaptureAppFramerate(input)	            结束获取帧率
startCaptureAppFramerate(app)	            获取指定应用帧率
EndCaptureAppFramerate(app,input)	        结束获取帧率