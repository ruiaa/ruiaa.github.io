#   notification
+   创建通知
        
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
        
        // 设置通知的基本信息：icon、标题、内容
        builder.setSmallIcon(R.drawable.notification_icon)
        builder.setContentTitle("My notification")
        builder.setContentText("Hello World!");
        
        builder.setContentTitle("测试标题")//设置通知栏标题  
            .setContentText("测试内容") /<span style="font-family: Arial;">/设置通知栏显示内容</span>  
            .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图  
        //  .setNumber(number) //设置通知集合的数量  
            .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的  
            .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间  
            .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级  
        //  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消    
            .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)  
            .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合  
            //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission  
            .setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON  
        
        // 设置通知的点击行为：这里启动一个 Activity
        Intent intent = new Intent(this, ResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        
        // 发送通知 id 需要在应用内唯一
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
        
+   更新通知

        要想更新通知，需要利用 NotificationManager.notify() 的 id 参数，该 id 在应用内需要唯一。要想更新特定 id 的通知，只需要创建新的 Notification，并发出与之前所用 id 相同的 Notification。如果之前的通知仍然可见，则系统会根据新的 Notification 对象的内容更新该通知。相反，如果之前的通知已被清除，系统则会创建一个新通知。
        
+   删除通知

        删除通知可以有多种方式： 
            1. 通过 NotificationCompat.Builder 设置 setAutoCancel(true)，这样当用户点击通知后，通知自动删除。 
            2. 通过 NotificationManager.cancel(id) 方法，删除指定 id 的通知 
            3. 通过 NotificationManager.cancelAll() 方法，删除该应用的所有通知