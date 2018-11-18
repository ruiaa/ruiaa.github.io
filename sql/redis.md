[文档](http://www.redis.net.cn/tutorial/3501.html)

+   键值 (key-value) 缓存 (cache) 和存储 (store) 系统
+   键包括 string，hash，list，set，sorted set，bitmap 和 hyperloglog

#   Redis 客户端
+   redis-cli -h 127.0.0.1 -p 6379 -a "mypass"

#   数据类型
+   String:二进制安全,可以包含任意类型的数据
    +   一个字符串最大为 512M 字节
+   List:按照插入顺序排序的字符串列表。可以添加一个元素到 Redis 列表的头部 (左边) 或者尾部 (右边)
    +   列表的最大长度是 2^23-1 个元素 (4294967295，超过 40 亿个元素)。
+   Set:没有顺序的不重复的字符串集合
+   Hash:字符串字段 (field) 与字符串值之间的映射
+   Sorted set:非重复字符串集合 (collection)。不同的是，每一个有序集合的成员都有一个关联的分数 (score)，用于按照分数高低排序。尽管成员是唯一的，但是分数是可以重复的。
+   Bitmap位图:
+   HyperLogLog超重




##  Key
+   可以使用任何二进制序列作为键
+   不要使用太长的键，例如，不要使用一个 1024 字节的键，不仅是因为内存占用，而且在数据集中查找键时需要多次耗时的键比较。即使手头需要匹配一个很大值的存在性，对其进行哈希 (例如使用 SHA1) 是个不错的主意，尤其是从内存和带宽的角度。
+   不要使用太短的键。用”u1000flw” 取代”user:1000:followers” 作为键并没有什么实际意义，后者更具有可读性，相对于键对象本身以及值对象来说，增加的空间微乎其微。然而不可否认，短的键会消耗少的内存，你的任务就是要找到平衡点。
+   键的最大大小是 512MB
+   
+   del key 该命令用于在 key 存在是删除 key。
+   dump key 序列化给定 key ，并返回被序列化的值。
+   exists key 检查给定 key 是否存在。
+   expire key seconds 为给定 key 设置过期时间。
+   expireat key timestamp expireat 的作用和 expire 类似，都用于为 key 设置过期时间。 不同在于 expireat 命令接受的时间参数是 unix 时间戳(unix timestamp)。
+   pexpire key milliseconds 设置 key 的过期时间亿以毫秒计。
+   pexpireat key milliseconds-timestamp 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计
+   keys pattern 查找所有符合给定模式( pattern)的 key 。
+   move key db 将当前数据库的 key 移动到给定的数据库 db 当中。
+   persist key 移除 key 的过期时间，key 将持久保持。
+   pttl key 以毫秒为单位返回 key 的剩余的过期时间。
+   ttl key 以秒为单位，返回给定 key 的剩余生存时间(ttl, time to live)。
+   randomkey 从当前数据库中随机返回一个 key 。
+   rename key newkey 修改 key 的名称
+   renamenx key newkey 仅当 newkey 不存在时，将 key 改名为 newkey 。
+   type key 返回 key 所储存的值的类型。
    



##  String
+   set key value 设置指定 key 的值
+   get key 获取指定 key 的值。

+   getrange key start end 返回 key 中字符串值的子字符
+   setrange key offset value 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。
+   getbit key offset 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
+   setbit key offset value 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。

+   setnx key value 只有在 key 不存在时设置 key 的值。
+   getset key value 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
+   strlen key 返回 key 所储存的字符串值的长度。

+   mget key1 [key2..] 获取所有(一个或多个)给定 key 的值。
+   mset key value [key value ...] 同时设置一个或多个 key-value 对。
+   msetnx key value [key value ...] 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。

+   incr key 将 key 中储存的数字值增一。
+   incrby key increment 将 key 所储存的值加上给定的增量值（increment） 。
+   incrbyfloat key increment 将 key 所储存的值加上给定的浮点增量值（increment） 。
+   decr key 将 key 中储存的数字值减一。
+   decrby key decrement key 所储存的值减去给定的减量值（decrement） 。+    append key value 如果 key 已经存在并且是一个字符串， append 命令将 value 追加到 key 原来的值的末尾。

+   setex key seconds value 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。
+   psetex key milliseconds value 这个命令和 setex 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 setex 命令那样，以秒为单位。


#   Hash
+   hdel key field2 [field2] 删除一个或多个哈希表字段
+   hexists key field 查看哈希表 key 中，指定的字段是否存在。
+   hget key field 获取存储在哈希表中指定字段的值/td>
+   hgetall key 获取在哈希表中指定 key 的所有字段和值
+   hincrby key field increment 为哈希表 key 中的指定字段的整数值加上增量 increment 。
+   hincrbyfloat key field increment 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。
+   hkeys key 获取所有哈希表中的字段
+   hlen key 获取哈希表中字段的数量
+   hmget key field1 [field2] 获取所有给定字段的值
+   hmset key field1 value1 [field2 value2 ] 同时将多个 field-value (域-值)对设置到哈希表 key 中。
+   hset key field value 将哈希表 key 中的字段 field 的值设为 value 。
+   hsetnx key field value 只有在字段 field 不存在时，设置哈希表字段的值。
+   hvals key 获取哈希表中所有值
+   hscan key cursor [match pattern] [count count] 迭代哈希表中的键值对。


#   List
+   blpop key1 [key2 ] timeout 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
+   brpop key1 [key2 ] timeout 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
+   brpoplpush source destination timeout 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
+   lindex key index 通过索引获取列表中的元素
+   linsert key before|after pivot value 在列表的元素前或者后插入元素
+   llen key 获取列表长度
+   lpop key 移出并获取列表的第一个元素
+   lpush key value1 [value2] 将一个或多个值插入到列表头部
+   lpushx key value 将一个或多个值插入到已存在的列表头部
+   lrange key start stop 获取列表指定范围内的元素
+   lrem key count value 移除列表元素
+   lset key index value 通过索引设置列表元素的值
+   ltrim key start stop 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
+   rpop key 移除并获取列表最后一个元素
+   rpoplpush source destination 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
+   rpush key value1 [value2] 在列表中添加一个或多个值
+   rpushx key value 为已存在的列表添加值



##  Set
+   sadd key member1 [member2] 向集合添加一个或多个成员
+   scard key 获取集合的成员数
+   sdiff key1 [key2] 返回给定所有集合的差集
+   sdiffstore destination key1 [key2] 返回给定所有集合的差集并存储在 destination 中
+   sinter key1 [key2] 返回给定所有集合的交集
+   sinterstore destination key1 [key2] 返回给定所有集合的交集并存储在 destination 中
+   sismember key member 判断 member 元素是否是集合 key 的成员
+   smembers key 返回集合中的所有成员
+   smove source destination member 将 member 元素从 source 集合移动到 destination 集合
+   spop key 移除并返回集合中的一个随机元素
+   srandmember key [count] 返回集合中一个或多个随机数
+   srem key member1 [member2] 移除集合中一个或多个成员
+   sunion key1 [key2] 返回所有给定集合的并集
+   sunionstore destination key1 [key2] 所有给定集合的并集存储在 destination 集合中
+   sscan key cursor [match pattern] [count count] 迭代集合中的元素

##  Sorted Set
+   zadd key score1 member1 [score2 member2] 向有序集合添加一个或多个成员，或者更新已存在成员的分数
+   zcard key 获取有序集合的成员数
+   zcount key min max 计算在有序集合中指定区间分数的成员数
+   zincrby key increment member 有序集合中对指定成员的分数加上增量 increment
+   zinterstore destination numkeys key [key ...] 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
+   zlexcount key min max 在有序集合中计算指定字典区间内成员数量
+   zrange key start stop [withscores] 通过索引区间返回有序集合成指定区间内的成员
+   zrangebylex key min max [limit offset count] 通过字典区间返回有序集合的成员
+   zrangebyscore key min max [withscores] [limit] 通过分数返回有序集合指定区间内的成员
+   zrank key member 返回有序集合中指定成员的索引
+   zrem key member [member ...] 移除有序集合中的一个或多个成员
+   zremrangebylex key min max 移除有序集合中给定的字典区间的所有成员
+   zremrangebyrank key start stop 移除有序集合中给定的排名区间的所有成员
+   zremrangebyscore key min max 移除有序集合中给定的分数区间的所有成员
+   zrevrange key start stop [withscores] 返回有序集中指定区间内的成员，通过索引，分数从高到底
+   zrevrangebyscore key max min [withscores] 返回有序集中指定分数区间内的成员，分数从高到低排序
+   zrevrank key member 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
+   zscore key member 返回有序集中，成员的分数值
+   zunionstore destination numkeys key [key ...] 计算给定的一个或多个有序集的并集，并存储在新的 key 中
+   zscan key cursor [match pattern] [count count] 迭代有序集合中的元素（包括元素成员和元素分值）


##  HyperLogLog
+   pfadd key element [element ...] 添加指定元素到 hyperloglog 中。
+   pfcount key [key ...] 返回给定 hyperloglog 的基数估算值。
+   pfmerge destkey sourcekey [sourcekey ...] 将多个 hyperloglog 合并为一个 hyperloglog

##  订阅
+   channel <--订阅-- client
+   msg --> channel --> client
+   
+   psubscribe pattern [pattern ...] 订阅一个或多个符合给定模式的频道。
+   pubsub subcommand [argument [argument ...]] 查看订阅与发布系统状态。
+   publish channel message 将信息发送到指定的频道。
+   punsubscribe [pattern [pattern ...]] 退订所有给定模式的频道。
+   subscribe channel [channel ...] 订阅给定的一个或多个频道的信息。
+   unsubscribe [channel [channel ...]] 指退订给定的频道。


##  事务
+   事务可以一次执行多个命令， 并且带有以下两个重要的保证：
    +   单独的隔离操作：事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。
    +   原子操作：事务中的命令要么全部被执行，要么全部都不执行。
+   一个事务从开始到执行会经历以下三个阶段：
    +   开始事务。
    +   命令入队。
    +   执行事务。

+   multi 标记一个事务块的开始。
+   命令
+   exec 执行所有事务块内的命令。
+   discard 取消事务，放弃执行事务块内的所有命令。
+   watch key [key ...] 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。
+   unwatch 取消 watch 命令对所有 key 的监视。


##  连接
+   auth password 验证密码是否正确
+   echo message 打印字符串
+   ping 查看服务是否运行
+   quit 关闭当前连接
+   select index 切换到指定的数据库

##  







