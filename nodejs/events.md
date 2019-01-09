
Node.js 是单进程单线程应用程序，但是因为 V8 引擎提供的异步执行回调接口
Node.js 所有的异步 I/O 操作在完成时都会发送一个事件到事件队列。
Node.js 里面的许多对象都会分发事件：
一个 net.Server 对象会在每次有新连接时触发一个事件
一个 fs.readStream 对象会在文件被打开的时候触发一个事件
所有这些产生事件的对象都是 events.EventEmitter 的实例。



#	events

    //引入 events 模块
    var events = require('events');
    //创建 eventEmitter 对象
    var eventEmitter = new events.EventEmitter();
    //绑定事件及事件的处理程序
    var eventHandler = function connected() {
    	......
    }
    eventEmitter.on('eventName', eventHandler);
    //触发事件
    eventEmitter.emit('eventName');
    //触发事件
    eventEmitter.emit('eventName');
    
    
    var events = require('events'); 
	var emitter = new events.EventEmitter(); 
	emitter.on('someEvent', function(arg1, arg2) { 
	    console.log('listener1', arg1, arg2); 
	}); 
	emitter.on('someEvent', function(arg1, arg2) { 
	    console.log('listener2', arg1, arg2); 
	}); 
	emitter.emit('someEvent', 'arg1 参数', 'arg2 参数'); 

addListener(event, listener)
为指定事件添加一个监听器到监听器数组的尾部。

on(event, listener)
为指定事件注册一个监听器，接受一个字符串 event 和一个回调函数。

once(event, listener)
为指定事件注册一个单次监听器，即 监听器最多只会触发一次，触发后立刻解除该监听器。

removeListener(event, listener)
移除指定事件的某个监听器，监听器必须是该事件已经注册过的监听器。

removeAllListeners([event])
移除所有事件的所有监听器， 如果指定事件，则移除指定事件的所有监听器。

setMaxListeners(n)
默认情况下， EventEmitters 如果你添加的监听器超过 10 个就会输出警告信息。 setMaxListeners 函数用于提高监听器的默认限制的数量。

listeners(event)
返回指定事件的监听器数组。

emit(event, [arg1], [arg2], [...])
按参数的顺序执行每个监听器，如果事件有注册监听返回 true，否则返回 false。



类方法
listenerCount(emitter, event)
返回指定事件的监听器数量。
events.EventEmitter.listenerCount(emitter, eventName) //已废弃，不推荐
events.emitter.listenerCount(eventName) //推荐



事件
newListener
	event - 字符串，事件名称
	listener - 处理事件函数
	该事件在添加新监听器时被触发。
removeListener
	event - 字符串，事件名称
	listener - 处理事件函数
	从指定监听器数组中删除一个监听器。需要注意的是，此操作将会改变处于被删监听器之后的那些监听器的索引。

error
	异常触发 error 事件，如果没有响应的监听器，Node.js 会把它当作异常，退出程序并输出错误信息。
	emitter.emit('error'); 
    --> Error: Uncaught, unspecified 'error' event. 
























