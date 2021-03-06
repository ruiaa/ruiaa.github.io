#   字符编码
    const buf = Buffer.from('runoob', 'ascii');
    console.log(buf.toString('hex'));       //输出 72756e6f6f62
    console.log(buf.toString('base64'));    //输出 cnVub29i

ascii - 仅支持 7 位 ASCII 数据。如果设置去掉高位的话，这种编码是非常快的。
utf8 - 多字节编码的 Unicode 字符。许多网页和其他文档格式都使用 UTF-8 。
utf16le - 2 或 4 个字节，小字节序编码的 Unicode 字符。支持代理对（U+10000 至 U+10FFFF）。
ucs2 - utf16le 的别名。
base64 - Base64 编码。
latin1 - 一种把 Buffer 编码成一字节编码的字符串的方式。
binary - latin1 的别名。
hex - 将每个字节编码为两个十六进制字符。


#   创建 Buffer 类
    Buffer.alloc(size[, fill[, encoding]])： 返回一个指定大小的 Buffer 实例，如果没有设置 fill，则默认填满 0
    Buffer.allocUnsafe(size)： 返回一个指定大小的 Buffer 实例，但是它不会被初始化，所以它可能包含敏感的数据
    Buffer.allocUnsafeSlow(size)
    Buffer.from(array)： 返回一个被 array 的值初始化的新的 Buffer 实例（传入的 array 的元素只能是数字，不然就会自动被 0 覆盖）
    Buffer.from(arrayBuffer[, byteOffset[, length]])： 返回一个新建的与给定的 ArrayBuffer 共享同一内存的 Buffer。
    Buffer.from(buffer)： 复制传入的 Buffer 实例的数据，并返回一个新的 Buffer 实例
    Buffer.from(string[, encoding])： 返回一个被 string 的值初始化的新的 Buffer 实例



#   写入缓冲区
buf.write(string[, offset[, length]][, encoding])
    string - 写入缓冲区的字符串。
    offset - 缓冲区开始写入的索引值，默认为 0 。
    length - 写入的字节数，默认为 buffer.length
    encoding - 使用的编码。默认为 'utf8' 。
    
    返回值
		返回实际写入的大小。
		如果 buffer 空间不足， 则只会写入部分字符串。

根据 encoding 的字符编码写入 string 到 buf 中的 offset 位置。 
如果 buf 没有足够的空间保存整个字符串，则只会写入 string 的一部分。
只部分解码的字符不会被写入。


#   从缓冲区读取数据
buf.toString([encoding[, start[, end]]])
    encoding - 使用的编码。默认为 'utf8' 。
    start - 指定开始读取的索引位置，默认为 0。
    end - 结束位置，默认为缓冲区的末尾。

	返回值
		解码缓冲区数据并使用指定的编码返回字符串。



#	将 Buffer 转换为 JSON 对象
buf.toJSON()
相当于JSON.stringify(buf)
当字符串化一个 Buffer 实例时，JSON.stringify() 会隐式地调用该 toJSON()。


#	缓冲区合并
Buffer.concat(list[, totalLength])
	list - 用于合并的 Buffer 对象数组列表。
	totalLength - 指定合并后Buffer对象的总长度。
	返回值
		返回一个多个成员合并的新 Buffer 对象。


#	缓冲区比较，按位比较
buf.compare(otherBuffer);
	otherBuffer - 与 buf 对象比较的另外一个 Buffer 对象。
	返回值
		返回一个数字，表示 buf 在 otherBuffer 之前，之后或相同。


#	拷贝缓冲区
buf.copy(targetBuffer[, targetStart[, sourceStart[, sourceEnd]]])
	targetBuffer - 要拷贝的 Buffer 对象。
	targetStart - 数字, 可选, 默认: 0
	sourceStart - 数字, 可选, 默认: 0
	sourceEnd - 数字, 可选, 默认: buffer.length


#	缓冲区裁剪
buf.slice([start[, end]])
    start - 数字, 可选, 默认: 0
    end - 数字, 可选, 默认: buffer.length
    返回值
		返回一个新的缓冲区，它和旧缓冲区指向同一块内存，但是从索引 start 到 end 的位置剪切。
对裁剪返回的 buffer 进行写操作同时，也对原始 buffer 进行了写操作。


#	缓冲区长度
buf.length;
	返回值
	返回 Buffer 对象所占据的内存长度。

