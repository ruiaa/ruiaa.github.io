[参考](http://www.restran.net/2015/10/04/supervisord-tutorial/)

#   supervisor
supervisor管理进程，就是通过fork/exec的方式把这些被管理的进程，当作supervisor的子进程来启动。这样的话，我们只要在supervisor的配置文件中，把要管理的进程的可执行文件的路径写进去就OK了。第二，被管理进程作为supervisor的子进程，当子进程挂掉的时候，父进程可以准确获取子进程挂掉的信息的，所以当然也就可以对挂掉的子进程进行自动重启，当然重启还是不重启，也要看你的配置文件里面有木有设置autostart=true了。
supervisor通过INI格式配置文件进行配置，很容易掌握，它为每个进程提供了很多配置选项，可以使你很容易的重启进程或者自动的轮转日志。

##  supervisor组件
+   supervisord

    主进程,负责管理进程的server，它会根据配置文件创建指定数量的应用程序的子进程，管理子进程的整个生命周期，对crash的进程重启，对进程变化发送事件通知等。
    
    同时内置web server和XML-RPC Interface，轻松实现进程管理。该服务的配置文件在/etc/supervisor/supervisord.conf。

+   supervisorctl

    客户端的命令行工具，提供一个类似shell的操作接口，通过它你可以连接到不同的supervisord进程上来管理它们各自的子程序，命令通过UNIX socket或者TCP来和服务通讯。
    
    用户通过命令行发送消息给supervisord，可以查看进程状态，加载配置文件，启停进程，查看进程标准输出和错误输出，远程操作等。
    
    服务端也可以要求客户端提供身份验证之后才能进行操作。

+   Web Server

    superviosr提供了web server功能，可通过web控制进程(需要设置[inet_http_server]配置项)。

+   XML-RPC Interface

    XML-RPC接口， 就像HTTP提供WEB UI一样，用来控制supervisor和由它运行的程序。
    
##  

+   安装
    +   yum install python-setuptools
    +   easy_install pip
    +   pip install supervisor
    
+   配置
    +   echo_supervisord_conf > supervisord.conf
    +   [unix_http_server]
        +   file=/home/supervisor/supervisor.sock  默认/tmp目录是存放临时文件的，里面的文件是会被 Linux 系统删除
    +   [supervisord]
        +   logfile=/var/log/supervisor/supervisord.log 
        +   pidfile=/home/supervisor/supervisord.pid 
    +   [supervisorctl]
        +   serverurl=unix:///home/supervisor/supervisor.sock 
    +   使用浏览器来管理
        +   [inet_http_server]
            +   port=0.0.0.0:3333
            +   username=user
            +   password=123
        +   [supervisorctl]
            +   serverurl=http://0.0.0.1:3333
            +   username=user
            +   password=123
    +   使用 include
        +   [include]
            +   files = /etc/supervisord.d/*.conf



        [unix_http_server]
        file=/home/supervisor/supervisor.sock      ; the path to the socket file
        ;chmod=0700                 ; socket file mode (default 0700)
        ;chown=nobody:nogroup       ; socket file uid:gid owner
        ;username=user              ; default is no username (open server)
        ;password=123               ; default is no password (open server)
        
        [inet_http_server]         ; inet (TCP) server disabled by default
        port=0.0.0.0:3333        ; ip_address:port specifier, *:port for all iface
        username=user             ; default is no username (open server)
        password=123            ; default is no password (open server)
        
        [supervisord]
        logfile=/var/log/supervisor/supervisord.log ; main log file; default $CWD/supervisord.log
        logfile_maxbytes=50MB        ; max main logfile bytes b4 rotation; default 50MB
        logfile_backups=10           ; # of main logfile backups; 0 means none, default 10
        loglevel=info                ; log level; default info; others: debug,warn,trace
        pidfile=/home/supervisor/supervisord.pid     ; supervisord pidfile; default supervisord.pid
        nodaemon=false               ; start in foreground if true; default false
        minfds=1024                  ; min. avail startup file descriptors; default 1024
        minprocs=200                 ; min. avail process descriptors;default 200
        ;umask=022                   ; process file creation umask; default 022
        ;user=chrism                 ; default is current user, required if root
        ;identifier=supervisor       ; supervisord identifier, default is 'supervisor'
        ;directory=/tmp              ; default is not to cd during start
        ;nocleanup=true              ; don't clean up tempfiles at start; default false
        ;childlogdir=/tmp            ; 'AUTO' child log dir, default $TEMP
        ;environment=KEY="value"     ; key value pairs to add to environment
        ;strip_ansi=false            ; strip ansi escape codes in logs; def. false
        
        ; The rpcinterface:supervisor section must remain in the config file for
        ; RPC (supervisorctl/web interface) to work.  Additional interfaces may be
        ; added by defining them in separate [rpcinterface:x] sections.
        
        [rpcinterface:supervisor]
        supervisor.rpcinterface_factory = supervisor.rpcinterface:make_main_rpcinterface
        
        ; The supervisorctl section configures how supervisorctl will connect to
        ; supervisord.  configure it match the settings in either the unix_http_server
        ; or inet_http_server section.
        
        [supervisorctl]
        serverurl=unix:////home/supervisor/supervisor.sock ; use a unix:// URL  for a unix socket
        serverurl=http://0.0.0.1:3333 ; use an http:// url to specify an inet socket
        username=user            ; should be same as in [*_http_server] if set
        password=123              ; should be same as in [*_http_server] if set
        ;prompt=mysupervisor         ; cmd line prompt (default "supervisor")
        ;history_file=~/.sc_history  ; use readline history if available
        
        ; The sample program section below shows all possible program subsection values.
        ; Create one or more 'real' program: sections to be able to control them under
        ; supervisor.
        
        ;[program:theprogramname]
        ;command=/bin/cat              ; the program (relative uses PATH, can take args)
        ;process_name=%(program_name)s ; process_name expr (default %(program_name)s)
        ;numprocs=1                    ; number of processes copies to start (def 1)
        ;directory=/tmp                ; directory to cwd to before exec (def no cwd)
        ;umask=022                     ; umask for process (default None)
        ;priority=999                  ; the relative start priority (default 999)
        ;autostart=true                ; start at supervisord start (default: true)
        ;startsecs=1                   ; # of secs prog must stay up to be running (def. 1)
        ;startretries=3                ; max # of serial start failures when starting (default 3)
        ;autorestart=unexpected        ; when to restart if exited after running (def: unexpected)
        ;exitcodes=0,2                 ; 'expected' exit codes used with autorestart (default 0,2)
        ;stopsignal=QUIT               ; signal used to kill process (default TERM)
        ;stopwaitsecs=10               ; max num secs to wait b4 SIGKILL (default 10)
        ;stopasgroup=false             ; send stop signal to the UNIX process group (default false)
        ;killasgroup=false             ; SIGKILL the UNIX process group (def false)
        ;user=chrism                   ; setuid to this UNIX account to run the program
        ;redirect_stderr=true          ; redirect proc stderr to stdout (default false)
        ;stdout_logfile=/a/path        ; stdout log path, NONE for none; default AUTO
        ;stdout_logfile_maxbytes=1MB   ; max # logfile bytes b4 rotation (default 50MB)
        ;stdout_logfile_backups=10     ; # of stdout logfile backups (0 means none, default 10)
        ;stdout_capture_maxbytes=1MB   ; number of bytes in 'capturemode' (default 0)
        ;stdout_events_enabled=false   ; emit events on stdout writes (default false)
        ;stderr_logfile=/a/path        ; stderr log path, NONE for none; default AUTO
        ;stderr_logfile_maxbytes=1MB   ; max # logfile bytes b4 rotation (default 50MB)
        ;stderr_logfile_backups=10     ; # of stderr logfile backups (0 means none, default 10)
        ;stderr_capture_maxbytes=1MB   ; number of bytes in 'capturemode' (default 0)
        ;stderr_events_enabled=false   ; emit events on stderr writes (default false)
        ;environment=A="1",B="2"       ; process environment additions (def no adds)
        ;serverurl=AUTO                ; override serverurl computation (childutils)
        
        ; The sample eventlistener section below shows all possible eventlistener
        ; subsection values.  Create one or more 'real' eventlistener: sections to be
        ; able to handle event notifications sent by supervisord.
        
        ;[eventlistener:theeventlistenername]
        ;command=/bin/eventlistener    ; the program (relative uses PATH, can take args)
        ;process_name=%(program_name)s ; process_name expr (default %(program_name)s)
        ;numprocs=1                    ; number of processes copies to start (def 1)
        ;events=EVENT                  ; event notif. types to subscribe to (req'd)
        ;buffer_size=10                ; event buffer queue size (default 10)
        ;directory=/tmp                ; directory to cwd to before exec (def no cwd)
        ;umask=022                     ; umask for process (default None)
        ;priority=-1                   ; the relative start priority (default -1)
        ;autostart=true                ; start at supervisord start (default: true)
        ;startsecs=1                   ; # of secs prog must stay up to be running (def. 1)
        ;startretries=3                ; max # of serial start failures when starting (default 3)
        ;autorestart=unexpected        ; autorestart if exited after running (def: unexpected)
        ;exitcodes=0,2                 ; 'expected' exit codes used with autorestart (default 0,2)
        ;stopsignal=QUIT               ; signal used to kill process (default TERM)
        ;stopwaitsecs=10               ; max num secs to wait b4 SIGKILL (default 10)
        ;stopasgroup=false             ; send stop signal to the UNIX process group (default false)
        ;killasgroup=false             ; SIGKILL the UNIX process group (def false)
        ;user=chrism                   ; setuid to this UNIX account to run the program
        ;redirect_stderr=false         ; redirect_stderr=true is not allowed for eventlisteners
        ;stdout_logfile=/a/path        ; stdout log path, NONE for none; default AUTO
        ;stdout_logfile_maxbytes=1MB   ; max # logfile bytes b4 rotation (default 50MB)
        ;stdout_logfile_backups=10     ; # of stdout logfile backups (0 means none, default 10)
        ;stdout_events_enabled=false   ; emit events on stdout writes (default false)
        ;stderr_logfile=/a/path        ; stderr log path, NONE for none; default AUTO
        ;stderr_logfile_maxbytes=1MB   ; max # logfile bytes b4 rotation (default 50MB)
        ;stderr_logfile_backups=10     ; # of stderr logfile backups (0 means none, default 10)
        ;stderr_events_enabled=false   ; emit events on stderr writes (default false)
        ;environment=A="1",B="2"       ; process environment additions
        ;serverurl=AUTO                ; override serverurl computation (childutils)
        
        ; The sample group section below shows all possible group values.  Create one
        ; or more 'real' group: sections to create "heterogeneous" process groups.
        
        ;[group:thegroupname]
        ;programs=progname1,progname2  ; each refers to 'x' in [program:x] definitions
        ;priority=999                  ; the relative start priority (default 999)
        
        ; The [include] section can just contain the "files" setting.  This
        ; setting can list multiple files (separated by whitespace or
        ; newlines).  It can also contain wildcards.  The filenames are
        ; interpreted as relative to this file.  Included files *cannot*
        ; include files themselves.
        
        [include]
        files = /etc/supervisord.d/*.conf


#   进程的配置

    ; 设置进程的名称，使用 supervisorctl 来管理进程时需要使用该进程名
    [program:your_program_name] 
    command=python server.py --port=9000
    ;numprocs=1                 ; 默认为1
    ;process_name=%(program_name)s   ; 默认为 %(program_name)s，即 [program:x] 中的 x
    directory=/home/python/tornado_server ; 执行 command 之前，先切换到工作目录
    user=oxygen                 ; 使用 oxygen 用户来启动该进程
    ; 程序崩溃时自动重启，重启次数是有限制的，默认为3次
    autorestart=true            
    redirect_stderr=true        ; 重定向输出的日志
    stdout_logfile = /var/log/supervisord/tornado_server.log
    loglevel=info               ;Python 的 print 语句输出的日志是不会被记录到日志文件中的，需要搭配 Python 的 logging 模块来输出有指定级别的日志。
    
    
    ; 默认为 false，如果设置为 true，当进程收到 stop 信号时，会自动将该信号发给该进程的子进程。如果这个配置项为 true，那么也隐含 killasgroup 为 true。例如在 Debug 模式使用 Flask 时，Flask 不会将接收到的 stop 信号也传递给它的子进程，因此就需要设置这个配置项。
    stopasgroup=false             ; send stop signal to the UNIX process 
    ; 默认为 false，如果设置为 true，当进程收到 kill 信号时，会自动将该信号发给该进程的子进程。如果这个程序使用了 python 的 multiprocessing 时，就能自动停止它的子线程。
    killasgroup=false             ; SIGKILL the UNIX process group (def false)


选项


    - command：启动程序使用的命令，可以是绝对路径或者相对路径
    - process_name：一个python字符串表达式，用来表示supervisor进程启动的这个的名称，默认值是%(program_name)s
    - numprocs：Supervisor启动这个程序的多个实例，如果numprocs>1，则process_name的表达式必须包含%(process_num)s，默认是1
    - numprocs_start：一个int偏移值，当启动实例的时候用来计算numprocs的值
    - priority：权重，可以控制程序启动和关闭时的顺序，权重越低：越早启动，越晚关闭。默认值是999
    - autostart：如果设置为true，当supervisord启动的时候，进程会自动重启。
    - autorestart：值可以是false、true、unexpected。false：进程不会自动重启，unexpected：当程序退出时的退出码不是exitcodes中定义的时，进程会重启，true：进程会无条件重启当退出的时候。
    - startsecs：程序启动后等待多长时间后才认为程序启动成功
    - startretries：supervisord尝试启动一个程序时尝试的次数。默认是3
    - exitcodes：一个预期的退出返回码，默认是0,2。
    - stopsignal：当收到stop请求的时候，发送信号给程序，默认是TERM信号，也可以是 HUP, INT, QUIT, KILL, USR1, or USR2。
    - stopwaitsecs：在操作系统给supervisord发送SIGCHILD信号时等待的时间
    - stopasgroup：如果设置为true，则会使supervisor发送停止信号到整个进程组
    - killasgroup：如果设置为true，则在给程序发送SIGKILL信号的时候，会发送到整个进程组，它的子进程也会受到影响。
    - user：如果supervisord以root运行，则会使用这个设置用户启动子程序
    - redirect_stderr：如果设置为true，进程则会把标准错误输出到supervisord后台的标准输出文件描述符。
    - stdout_logfile：把进程的标准输出写入文件中，如果stdout_logfile没有设置或者设置为AUTO，则supervisor会自动选择一个文件位置。
    - stdout_logfile_maxbytes：标准输出log文件达到多少后自动进行轮转，单位是KB、MB、GB。如果设置为0则表示不限制日志文件大小
    - stdout_logfile_backups：标准输出日志轮转备份的数量，默认是10，如果设置为0，则不备份
    - stdout_capture_maxbytes：当进程处于stderr capture mode模式的时候，写入FIFO队列的最大bytes值，单位可以是KB、MB、GB
    - stdout_events_enabled：如果设置为true，当进程在写它的stderr到文件描述符的时候，PROCESS_LOG_STDERR事件会被触发
    - stderr_logfile：把进程的错误日志输出一个文件中，除非redirect_stderr参数被设置为true
    - stderr_logfile_maxbytes：错误log文件达到多少后自动进行轮转，单位是KB、MB、GB。如果设置为0则表示不限制日志文件大小
    - stderr_logfile_backups：错误日志轮转备份的数量，默认是10，如果设置为0，则不备份
    - stderr_capture_maxbytes：当进程处于stderr capture mode模式的时候，写入FIFO队列的最大bytes值，单位可以是KB、MB、GB
    - stderr_events_enabled：如果设置为true，当进程在写它的stderr到文件描述符的时候，PROCESS_LOG_STDERR事件会被触发
    - environment：一个k/v对的list列表
    - directory：supervisord在生成子进程的时候会切换到该目录
    - umask：设置进程的umask
    - serverurl：是否允许子进程和内部的HTTP服务通讯，如果设置为AUTO，supervisor会自动的构造一个url

#   多个进程
一个 [program:x] 实际上是表示一组相同特征或同类的进程组

这组进程的成员是通过 numprocs 和 process_name 这两个参数来确定的


    ; 设置进程的名称，使用 supervisorctl 来管理进程时需要使用该进程名
    [program:foo] 
    ; 可以在 command 这里用 python 表达式传递不同的参数给每个进程
    command=python server.py --port=90%(process_num)02d
    directory=/home/python/tornado_server ; 执行 command 之前，先切换到工作目录
    ; 若 numprocs 不为1，process_name 的表达式中一定要包含 process_num 来区分不同的进程
    numprocs=2                   
    process_name=%(program_name)s_%(process_num)02d; 
    user=oxygen                 ; 使用 oxygen 用户来启动该进程
    autorestart=true            ; 程序崩溃时自动重启
    redirect_stderr=true        ; 重定向输出的日志
    stdout_logfile = /var/log/supervisord/tornado_server.log
    loglevel=info


+   启动
    +   supervisord
    
#   supervisorctl
supervisorctl stop programxxx，停止某一个进程(programxxx)，programxxx 为 [program:beepkg] 里配置的值，这个示例就是 beepkg。
supervisorctl start programxxx，启动某个进程
supervisorctl restart programxxx，重启某个进程
supervisorctl stop groupworker: ，结束所有属于名为 groupworker 这个分组的进程(start,restart 同理)
supervisorctl stop groupworker:name1 ，结束 groupworker:name1 这个进程 (start，restart 同理)
supervisorctl stop all，停止全部进程，注：start、restart、stop 都不会载入最新的配置文件。
supervisorctl reload，载入最新的配置文件，停止原有进程并按新的配置启动、管理所有进程。
supervisorctl update，根据最新的配置文件，启动新配置或有改动的进程，配置没有改动的进程不会受影响而重启。
[参考](http://www.restran.net/2015/10/04/supervisord-tutorial/)