#   进程状态：（R 运行），（S 中断），（D 不可中断），（T 暂停），（Z 僵死），（X 销毁）
+   R (task_running)，可执行状态
    可在cpu上运行，其task_struct结构（进程控制块）被放入某一个cpu的可执行队列中，
    等待进程调度器从各个cpu的可执行队列中分别选择一个进程在该cpu上运行。
    
    有时将正在cpu上执行的进程定义为running状态，
    而可执行但尚未被调度执行的进程定义为ready状态，
    这两种状态在linux下统一为 task_running状态。


+   S (task_interruptible)，可中断的睡眠状态（绝大多数进程处于该状态）
    该状态的进程因为等待某事件的发生（比如等待socket连接、等待信号量），而被挂起。
    这些进程的task_struct结构被放入对应事件的等待队列中。
    当这些事件发生时（由外部中断触发、或由其他进程触发），等待队列中对应的多个进程将被唤醒。

    
+   D (task_uninterruptible)，不可中断的睡眠状态
    与task_interruptible状态类似，进程处于睡眠状态，但是此刻进程是不可中断的。
    不可中断，指的并不是cpu不响应外部硬件的中断，而是指进程不响应异步信号。（kill -9 杀不死）
    绝大多数情况下，进程处在睡眠状态时，总是应该能够响应异步信号的，并转入处理异步信号的流程。
    
    task_uninterruptible状态存在的意义就在于，内核的某些处理流程是不能被打断的。
    比如，进程对某些硬件进行操作，应避免进程与设备交互的过程被打断，造成设备陷入不可控的状态；
    执行vfork系统调用后，父进程将进入task_uninterruptible状态，直到子进程调用exit或exec。
    
    
+   T (task_stopped or task_traced)，暂停状态或跟踪状态
    向进程发送一个sigstop信号，它就会因响应该信号而进入task_stopped状态（除非该进程本身处于task_uninterruptible状态而不响应信号）。
    sigstop与sigkill信号一样，是非常强制的。不允许用户进程通过signal系列的系统调用重新设置对应的信号处理函数。
    向进程发送一个sigcont信号，可以让其从task_stopped状态恢复到task_running状态。

    当进程正在被跟踪时，它处于task_traced这个特殊的状态。
    “正在被跟踪”指的是进程暂停下来，等待跟踪它的进程对它进行操作。
    比如在gdb中对被跟踪的进程下一个断点，进程在断点处停下来的时候就处于task_traced状态。

    对于进程本身来说，task_stopped和task_traced状态很类似，都是表示进程暂停下来。
    而task_traced状态相当于在task_stopped之上多了一层保护，处于task_traced状态的进程不能响应sigcont信号而被唤醒。
    只能等到调试进程通过ptrace系统调用执行ptrace_cont、ptrace_detach等操作（通过ptrace系统调用的参数指定操作），
    或调试进程退出，被调试的进程才能恢复task_running状态。
    
    
+   Z (task_dead - exit_zombie)，退出状态，进程成为僵尸进程
    进程在退出的过程中，处于task_dead状态。

    在这个退出过程中，进程占有的所有资源将被回收，除了task_struct结构（以及少数资源）以外。
    task_struct里面保存了进程的退出码、以及一些统计信息，而其父进程很可能会关心这些信息。
    比如在shell中，$?变量就保存了最后一个退出的前台进程的退出码，而这个退出码往往被作为if语句的判断条件。

    当然，内核也可以将这些信息保存在别的地方，而将task_struct结构释放掉，以节省一些空间。
    但是使用task_struct结构更为方便，因为在内核中已经建立了从pid到task_struct查找关系，还有进程间的父子关系。
    释放掉task_struct，则需要建立一些新的数据结构，以便让父进程找到它的子进程的退出信息。

    父进程可以通过wait系列的系统调用（如wait4、waitid）来等待某个或某些子进程的退出，并获取它的退出信息。
    然后wait系列的系统调用会顺便将子进程的尸体（task_struct）也释放掉。
    子进程在退出的过程中，内核会给其父进程发送一个信号，通知父进程来“收尸”。
    这个信号默认是sigchld，但是在通过clone系统调用创建子进程时，可以设置这个信号。
    
    
+	X (task_dead - exit_dead)，退出状态，进程即将被销毁。
    exit_dead状态是非常短暂的


#   ps  查看当前正在运行的进程
+   ps aux | grep name
+   ps -ef | grep java
+   ps 1777 查看进程的详细信息

      a  显示所有进程
      -a 显示同一终端下的所有程序
      -A 显示所有进程
      c  显示进程的真实名称
      -N 反向选择
      -e 等于“-A”
      e  显示环境变量
      f  显示程序间的关系
      -H 显示树状结构
      r  显示当前终端的进程
      T  显示当前终端的所有程序
      u  指定用户的所有进程
      -au 显示较详细的资讯
      -aux 显示所有包含其他使用者的行程 
      -C<命令> 列出指定命令的状况
      --lines<行数> 每页显示的行数
      --width<字符数> 每页显示的字符数
      --help 显示帮助信息
      --version 显示版本显示

#   kill 关闭进程
+   kill -9 1777        # 彻底关闭PID为1777的进程
+   kill -u usera       # 关闭用户usera的所有进程  kill -9 $(ps -ef | grep usera)

#   nohup  后台运行   

    nohup python3 test.py >test.out 2>&1 &
    nohup            表示在后台运行 
    python3 test.py  将要在后台运行的命令 
    >                表示输出，其右侧接文件名。 
    test.out         系统的自动输出（程序自身的运行信息）文件
    2>&1             表示把错误重定向到test.out的重定向输出中。
    带&的命令行      表示terminal终端即使关闭，或者电脑死机程序依然运行

#   service

#	free , top























    