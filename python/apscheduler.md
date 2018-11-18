#	APScheduler
+   trigger 触发器，描述一个任务何时被触发，有按日期、按时间间隔、按cronjob描述式三种触发方式
+   job stores: 任务持久化仓库，默认保存任务在内存中，也可将任务保存都各种数据库中，任务中的数据序列化后保存到持久化数据库，从数据库加载后又反序列化。
+   executors:  执行任务模块，当任务完成时executors通知schedulers，schedulers收到后会发出一个适当的事件
+   schedulers: 任务调度器，控制器角色，通过它配置job stores和executors，添加、修改和删除任务。


##  scheduler
+   主循环:反复检查是不是有到时需要执行的任务，完成一次检查的函数是_process_jobs
    +   询问自己的每一个jobstore，有没有到期需要执行的任务（jobstore.get_due_jobs()）
    +   如果有，计算这些job中每个job需要运行的时间点（run_times = job._get_run_times(now)）
    +   提交给executor排期运行（executor.submit_job(job, run_times)）
+   scheduler由于IO模型的不同，可以有多种实现
    +   BlockingScheduler: scheduler在当前进程的主线程中运行，所以调用start函数会阻塞当前线程，不能立即返回。
    +   BackgroundScheduler: 放到后台线程中运行，所以调用start后主线程不会阻塞
    +   AsyncIOScheduler: 使用asyncio模块
    +   GeventScheduler: 使用gevent作为IO模型，和GeventExecutor配合使用
    +   TornadoScheduler: 配合TwistedExecutor，用reactor.callLater完成定时唤醒
    +   TwistedScheduler: 使用tornado的IO模型，用ioloop.add_timeout完成定时唤醒
    +   QtScheduler: 使用QTimer完成定时唤醒

##  jobstore
+   供给scheduler一个序列化jobs的统一抽象，提供对scheduler中job的增删改查接口
    +   MemoryJobStore：没有序列化，jobs就存在内存里，增删改查也都是在内存中操作
    +   SQLAlchemyJobStore：所有sqlalchemy支持的数据库都可以做为backend，增删改查操作转化为对应backend的sql语句
    +   MongoDBJobStore：用mongodb作backend
    +   RedisJobStore: 用redis作backend



##  executor
+   不同类型的executor实现自己的_do_submit_job，完成一次实际的任务实例执行
    +   ProcessPoolExecutor: 多进程，可指定进程数，当工作负载为CPU密集型操作时可以考虑使用它来利用多核CPU
    +   ThreadPoolExecutor: 多线程，可指定线程数，默认，可以满足大多数用途
    +   AsyncIOExecutor
    +   DebugExecutor
    +   GeventExecutor
    +   ProcessPoolExecutor
    +   ThreadPoolExecutor
    +   TwistedExecutor

##  trigger
+   每种trigger实现自己的get_next_fire_time函数
    +   date：一次性指定日期
    +   interval：在某个时间范围内间隔多长时间执行一次
    +   cron：和unix crontab格式兼容，最为强大

+   默认配置: 使用MemoryJobStore和ThreadPoolExecutor









