os.listdir(path)   返回path指定的文件夹包含的文件或文件夹的名字的列表。
os.walk(top[, topdown=True[, onerror=None[, followlinks=False]]])   输出在文件夹中的文件名通过在树中游走，向上或者向下。

os.remove(path)   删除路径为path的文件。如果path 是一个文件夹，将抛出OSError; 查看下面的rmdir()删除一个 directory。
os.removedirs(path)   递归删除目录。
os.rename(src, dst)   重命名文件或目录，从 src 到 dst
os.renames(old, new)   递归地对目录进行更名，也可以对文件进行更名。

os.mkdir(path[, mode])   以数字mode的mode创建一个名为path的文件夹.默认的 mode 是 0777 (八进制)。
os.rmdir(path)   删除path指定的空目录，如果目录非空，则抛出一个OSError异常。






os.access(path, mode)   检验权限模式
os.chdir(path)   改变当前工作目录
os.chflags(path, flags)   设置路径的标记为数字标记。
os.chmod(path, mode)   更改权限
os.chown(path, uid, gid)   更改文件所有者
os.chroot(path)   改变当前进程的根目录
os.close(fd)   关闭文件描述符 fd
os.closerange(fd_low, fd_high)   关闭所有文件描述符，从 fd_low (包含) 到 fd_high (不包含), 错误会忽略
os.dup(fd)   复制文件描述符 fd
os.dup2(fd, fd2)   将一个文件描述符 fd 复制到另一个 fd2
os.fchdir(fd)   通过文件描述符改变当前工作目录
os.fchmod(fd, mode)   改变一个文件的访问权限，该文件由参数fd指定，参数mode是Unix下的文件访问权限。
os.fchown(fd, uid, gid)   修改一个文件的所有权，这个函数修改一个文件的用户ID和用户组ID，该文件由文件描述符fd指定。
os.fdatasync(fd)   强制将文件写入磁盘，该文件由文件描述符fd指定，但是不强制更新文件的状态信息。
os.fdopen(fd[, mode[, bufsize]])   通过文件描述符 fd 创建一个文件对象，并返回这个文件对象
os.fpathconf(fd, name)   返回一个打开的文件的系统配置信息。name为检索的系统配置的值，它也许是一个定义系统值的字符串，这些名字在很多标准中指定（POSIX.1, Unix 95, Unix 98, 和其它）。
os.fstat(fd)   返回文件描述符fd的状态，像stat()。
os.fstatvfs(fd)   返回包含文件描述符fd的文件的文件系统的信息，像 statvfs()
os.fsync(fd)   强制将文件描述符为fd的文件写入硬盘。
os.ftruncate(fd, length)   裁剪文件描述符fd对应的文件, 所以它最大不能超过文件大小。
os.getcwd()   返回当前工作目录
os.getcwdu()   返回一个当前工作目录的Unicode对象
os.isatty(fd)   如果文件描述符fd是打开的，同时与tty(-like)设备相连，则返回true, 否则False。
os.lchflags(path, flags)   设置路径的标记为数字标记，类似 chflags()，但是没有软链接
os.lchmod(path, mode)   修改连接文件权限
os.lchown(path, uid, gid)   更改文件所有者，类似 chown，但是不追踪链接。
os.link(src, dst)   创建硬链接，名为参数 dst，指向参数 src
os.listdir(path)   返回path指定的文件夹包含的文件或文件夹的名字的列表。
os.lseek(fd, pos, how)   设置文件描述符 fd当前位置为pos, how方式修改: SEEK_SET 或者 0 设置从文件开始的计算的pos; SEEK_CUR或者 1 则从当前位置计算; os.SEEK_END或者2则从文件尾部开始. 在unix，Windows中有效
os.lstat(path)   像stat(),但是没有软链接
os.major(device)   从原始的设备号中提取设备major号码 (使用stat中的st_dev或者st_rdev field)。
os.makedev(major, minor)   以major和minor设备号组成一个原始设备号
os.makedirs(path[, mode])   递归文件夹创建函数。像mkdir(), 但创建的所有intermediate-level文件夹需要包含子文件夹。
os.minor(device)   从原始的设备号中提取设备minor号码 (使用stat中的st_dev或者st_rdev field )。
os.mkdir(path[, mode])   以数字mode的mode创建一个名为path的文件夹.默认的 mode 是 0777 (八进制)。
os.mkfifo(path[, mode])   创建命名管道，mode 为数字，默认为 0666 (八进制)
os.mknod(filename[, mode=0600, device])   创建一个名为filename文件系统节点（文件，设备特别文件或者命名pipe）。
os.open(file, flags[, mode])   打开一个文件，并且设置需要的打开选项，mode参数是可选的
os.openpty()   打开一个新的伪终端对。返回 pty 和 tty的文件描述符。
os.pathconf(path, name)   返回相关文件的系统配置信息。
os.pipe()   创建一个管道. 返回一对文件描述符(r, w) 分别为读和写
os.popen(command[, mode[, bufsize]])   从一个 command 打开一个管道
os.read(fd, n)   从文件描述符 fd 中读取最多 n 个字节，返回包含读取字节的字符串，文件描述符 fd对应文件已达到结尾, 返回一个空字符串。
os.readlink(path)   返回软链接所指向的文件
os.remove(path)   删除路径为path的文件。如果path 是一个文件夹，将抛出OSError; 查看下面的rmdir()删除一个 directory。
os.removedirs(path)   递归删除目录。
os.rename(src, dst)   重命名文件或目录，从 src 到 dst
os.renames(old, new)   递归地对目录进行更名，也可以对文件进行更名。
os.rmdir(path)   删除path指定的空目录，如果目录非空，则抛出一个OSError异常。
os.stat(path)   获取path指定的路径的信息，功能等同于C API中的stat()系统调用。
os.stat_float_times([newvalue])   决定stat_result是否以float对象显示时间戳
os.statvfs(path)   获取指定路径的文件系统统计信息
os.symlink(src, dst)   创建一个软链接
os.tcgetpgrp(fd)   返回与终端fd（一个由os.open()返回的打开的文件描述符）关联的进程组
os.tcsetpgrp(fd, pg)   设置与终端fd（一个由os.open()返回的打开的文件描述符）关联的进程组为pg。
os.tempnam([dir[, prefix]])   Python3 中已删除。返回唯一的路径名用于创建临时文件。
os.tmpfile()   Python3 中已删除。返回一个打开的模式为(w+b)的文件对象 .这文件对象没有文件夹入口，没有文件描述符，将会自动删除。
os.tmpnam()   Python3 中已删除。为创建一个临时文件返回一个唯一的路径
os.ttyname(fd)   返回一个字符串，它表示与文件描述符fd 关联的终端设备。如果fd 没有与终端设备关联，则引发一个异常。
os.unlink(path)   删除文件路径
os.utime(path, times)   返回指定的path文件的访问和修改的时间。
os.walk(top[, topdown=True[, onerror=None[, followlinks=False]]])   输出在文件夹中的文件名通过在树中游走，向上或者向下。
os.write(fd, str)   写入字符串到文件描述符 fd中. 返回实际写入的字符串长度
os.path 模块   获取文件的属性信息。


   





os.path.join(path1[, path2[, ...]])	把目录和文件名合成一个路径
os.path.basename(path)	返回文件名
os.path.dirname(path)	返回文件路径
os.path.split(path)	把路径分割成 dirname 和 basename，返回一个元组
os.path.splitext(path)	分割路径，返回路径名和文件扩展名的元组
os.path.exists(path)	路径存在则返回True,路径损坏返回False
os.path.isabs(path)	判断是否为绝对路径
os.path.isfile(path)	判断路径是否为文件
os.path.isdir(path)	判断路径是否为目录


   
   
   
os.path.abspath(path)	返回绝对路径
os.path.basename(path)	返回文件名
os.path.commonprefix(list)	返回list(多个路径)中，所有path共有的最长的路径
os.path.dirname(path)	返回文件路径
os.path.exists(path)	路径存在则返回True,路径损坏返回False
os.path.lexists	路径存在则返回True,路径损坏也返回True
os.path.expanduser(path)	把path中包含的"~"和"~user"转换成用户目录
os.path.expandvars(path)	根据环境变量的值替换path中包含的"$name"和"${name}"
os.path.getatime(path)	返回最近访问时间（浮点型秒数）
os.path.getmtime(path)	返回最近文件修改时间
os.path.getctime(path)	返回文件 path 创建时间
os.path.getsize(path)	返回文件大小，如果文件不存在就返回错误
os.path.isabs(path)	判断是否为绝对路径
os.path.isfile(path)	判断路径是否为文件
os.path.isdir(path)	判断路径是否为目录
os.path.islink(path)	判断路径是否为链接
os.path.ismount(path)	判断路径是否为挂载点
os.path.join(path1[, path2[, ...]])	把目录和文件名合成一个路径
os.path.normcase(path)	转换path的大小写和斜杠
os.path.normpath(path)	规范path字符串形式
os.path.realpath(path)	返回path的真实路径
os.path.relpath(path[, start])	从start开始计算相对路径
os.path.samefile(path1, path2)	判断目录或文件是否相同
os.path.sameopenfile(fp1, fp2)	判断fp1和fp2是否指向同一文件
os.path.samestat(stat1, stat2)	判断stat tuple stat1和stat2是否指向同一个文件
os.path.split(path)	把路径分割成 dirname 和 basename，返回一个元组
os.path.splitdrive(path)	一般用在 windows 下，返回驱动器名和路径组成的元组
os.path.splitext(path)	分割路径，返回路径名和文件扩展名的元组
os.path.splitunc(path)	把路径分割为加载点与文件
os.path.walk(path, visit, arg)	遍历path，进入每个目录都调用visit函数，visit函数必须有3个参数(arg, dirname, names)，dirname表示当前目录的目录名，names代表当前目录下的所有文件名，args则为walk的第三个参数
os.path.supports_unicode_filenames	设置是否支持unicode路径名