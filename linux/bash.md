#	
+	#!/bin/bash
+	扩展名为 sh（sh代表shell）
#	运行
+	作为可执行程序
chmod +x ./test.sh  #使脚本具有执行权限
./test.sh  #执行脚本

+	作为解释器参数
/bin/sh test.sh
/bin/php test.php

#	变量
定义变量时，变量名不加$
avar="pp"

使用一个定义过的变量，只要在变量名前面加$即可，如：
echo $avar
echo ${avar}
echo "...${avar}..."

已定义的变量，可以被重新定义，如：
your_name="tom"
echo $your_name
your_name="alibaba"
echo $your_name

只读变量
使用 readonly 命令可以将变量定义为只读变量，只读变量的值不能被改变。

myUrl="http://www.google.com"
readonly myUrl

删除变量
使用 unset 命令可以删除变量。语法：
unset variable_name
变量被删除后不能再次使用。unset 命令不能删除只读变量。


变量类型
运行shell时，会同时存在三种变量：
1) 局部变量 局部变量在脚本或命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量。
2) 环境变量 所有的程序，包括shell启动的程序，都能访问环境变量，有些程序需要环境变量来保证其正常运行。必要的时候shell脚本也可以定义环境变量。
3) shell变量 shell变量是由shell程序设置的特殊变量。shell变量中有一部分是环境变量，有一部分是局部变量，这些变量保证了shell的正常运行


#	字符串
单引号
str='this is a string'
单引号字符串的限制：
单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的；
单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用。

双引号
双引号里可以有变量
双引号里可以出现转义字符
your_name='runoob'
str="Hello, I know you are \"$your_name\"! \n"
echo $str
输出结果为：
Hello, I know you are "runoob"! 


获取字符串长度
string="abcd"
echo ${#string} #输出 4

提取子字符串
string="runoob is a great site"
echo ${string:1:4} # 输出 unoo

查找子字符串
查找字符 i 或 o 的位置(哪个字母先出现就计算哪个)：
string="runoob is a great site"
echo `expr index "$string" io`  # 输出 4 
注意： 以上脚本中 ` 是反引号，而不是单引号,	 `


#	数组
用括号来表示数组，数组元素用"空格"符号分割开
数组名=(值1 值2 ... 值n)
array_name=(value0 value1 value2 value3)
或者
array_name=(
value0
value1
value2
value3
)
还可以单独定义数组的各个分量：
array_name[0]=value0
array_name[1]=value1
array_name[n]=valuen
可以不使用连续的下标，而且下标的范围没有限制。

读取数组
读取数组元素值的一般格式是：
${数组名[下标]}
例如：
valuen=${array_name[n]}
使用 @ 符号可以获取数组中的所有元素，例如：
echo ${array_name[@]}

获取数组的长度
获取数组长度的方法与获取字符串长度的方法相同，例如：

length=${#array_name[@]} #取得数组元素的个数
length=${#array_name[*]} #取得数组元素的个数
lengthn=${#array_name[n]} # 取得数组单个元素的长度




#   注释
以 # 开头的行就是注释，会被解释器忽略。

#	传递参数
在执行 Shell 脚本时，向脚本传递参数
脚本内获取参数的格式为：$n。n 代表一个数字，$0 为执行的文件名，$1 为执行脚本的第一个参数

echo "Shell 传递参数实例！";
echo "执行的文件名：$0";
echo "第一个参数为：$1";
echo "第二个参数为：$2";
echo "第三个参数为：$3";


$ chmod +x test.sh 
$ ./test.sh 1 2 3
Shell 传递参数实例！
执行的文件名：./test.sh
第一个参数为：1
第二个参数为：2
第三个参数为：3

特殊字符
参数处理	说明
$#	传递到脚本的参数个数
$*	以一个字符串显示所有向脚本传递的参数（一个字符串）。如"$*"用「"」括起来的情况、以"$1 $2 … $n"的形式输出所有参数。
$$	脚本运行的当前进程ID号
$!	后台运行的最后一个进程的ID号
$@	与$*相同，但是使用时加引号，并在引号中返回每个参数（几个参数就有几个字符串）。如"$@"用「"」括起来的情况、以"$1" "$2" … "$n" 的形式输出所有参数。
$-	显示Shell使用的当前选项，与set命令功能相同。
$?	显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。






expr 是一款表达式计算工具，使用它能完成表达式的求值操作。

	val=`expr 2 + 2`
	echo "两数之和为 : $val"
	
表达式和运算符之间要有空格，例如 2+2 是不对的，必须写成 2 + 2
完整的表达式要被反引号 ` ` 包含，

假定变量 a 为 10，变量 b 为 20:

+	加法	`expr $a + $b` 结果为 30。
-	减法	`expr $a - $b` 结果为 -10。
\*	乘法	`expr $a \* $b` 结果为  200。
/	除法	`expr $b / $a` 结果为 2。
%	取余	`expr $b % $a` 结果为 0。
=	赋值	a=$b 将把变量 b 的值赋给 a。
==	相等	[ $a == $b ] 返回 false。
!=	不相等	[ $a != $b ] 返回 true。

条件表达式要放在方括号之间，并且要有空格，例如: [$a==$b] 是错误的，必须写成 [ $a == $b ]。
乘号(*)前边必须加反斜杠(\)才能实现乘法运算；
在 MAC 中 shell 的 expr 语法是：$((表达式))，此处表达式中的 "*" 不需要转义符号 "\" 。

关系运算符
关系运算符只支持数字，不支持字符串，除非字符串的值是数字。
运算符	说明	举例
-eq	  相等		[ $a -eq $b ] 返回 false。
-ne	  不相等	[ $a -ne $b ] 返回 true。
-gt	  大于		[ $a -gt $b ] 返回 false。
-lt	  小于		[ $a -lt $b ] 返回 true。
-ge	  大于等于	[ $a -ge $b ] 返回 false。
-le	  小于等于	[ $a -le $b ] 返回 true。

符号含义：
1. eq  （equal的缩写），表示等于为真
2. ne    (not equal的缩写），表示不等于为真
3. gt     (greater than的缩写），表示大于为真
4. ge  （greater&equal的缩写），表示大于等于为真
5. lt    （lower than的缩写），表示小于为真
6. le   （lower&equal的缩写），表示小于等于为真


布尔运算符
下表列出了常用的布尔运算符，假定变量 a 为 10，变量 b 为 20：
运算符	说明	举例
!	非运算	[ ! false ] 返回 true。
-o	或运算	[ $a -lt 20 -o $b -gt 100 ] 返回 true。
-a	与运算	[ $a -lt 20 -a $b -gt 100 ] 返回 false。


逻辑运算符
运算符	说明	举例
&&	逻辑的 AND	[[ $a -lt 100 && $b -gt 100 ]] 返回 false
||	逻辑的 OR	[[ $a -lt 100 || $b -gt 100 ]] 返回 true


字符串运算符
运算符	说明	举例:变量 a 为 "abc"，变量 b 为 "efg"：
=	相等	[ $a = $b ] 返回 false。
!=	不相等	[ $a != $b ] 返回 true。
-z	为0		[ -z $a ] 返回 false。
-n	不为0	[ -n "$a" ] 返回 true。
str	不为空	[ $a ] 返回 true。


文件测试运算符
文件测试运算符用于检测 Unix 文件的各种属性。
操作符	说明	举例
-b file	检测文件是否是块设备文件，如果是，则返回 true。	[ -b $file ] 返回 false。
-c file	检测文件是否是字符设备文件，如果是，则返回 true。	[ -c $file ] 返回 false。
-d file	检测文件是否是目录，如果是，则返回 true。	[ -d $file ] 返回 false。
-f file	检测文件是否是普通文件（既不是目录，也不是设备文件），如果是，则返回 true。	[ -f $file ] 返回 true。
-g file	检测文件是否设置了 SGID 位，如果是，则返回 true。	[ -g $file ] 返回 false。
-k file	检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。	[ -k $file ] 返回 false。
-p file	检测文件是否是有名管道，如果是，则返回 true。	[ -p $file ] 返回 false。
-u file	检测文件是否设置了 SUID 位，如果是，则返回 true。	[ -u $file ] 返回 false。
-r file	检测文件是否可读，如果是，则返回 true。	[ -r $file ] 返回 true。
-w file	检测文件是否可写，如果是，则返回 true。	[ -w $file ] 返回 true。
-x file	检测文件是否可执行，如果是，则返回 true。	[ -x $file ] 返回 true。
-s file	检测文件是否为空（文件大小是否大于0），不为空返回 true。	[ -s $file ] 返回 true。
-e file	检测文件（包括目录）是否存在，如果是，则返回 true。	[ -e $file ] 返回 true。



#	echo string : 用于字符串的输出，自动换行

显示转义字符
echo "\"It is a test\""
结果将是:"It is a test"

显示变量
read 命令从标准输入中读取一行,并把输入行的每个字段的值指定给 shell 变量
#!/bin/sh
read name 
echo "$name It is a test"

显示换行
echo -e "OK! \n" # -e 开启转义
echo "It is a test"

显示不换行
#!/bin/sh
echo -e "OK! \c" # -e 开启转义 \c 不换行
echo "It is a test"

显示结果定向至文件
echo "It is a test" > myfile

原样输出字符串，不进行转义或取变量(用单引号)
echo '$name\"'

显示命令执行结果
echo `date`
注意： 这里使用的是反引号 `, 而不是单引号 '。
结果将显示当前日期
Thu Jul 24 10:08:46 CST 2014



#	printf  format-string  [arguments...]

C风格



%d %s %c %f 格式替代符
d: Decimal 十进制整数 -- 对应位置参数必须是十进制整数
s: String 字符串 -- 对应位置参数必须是字符串或者字符型
c: Char 字符 -- 对应位置参数必须是字符型或者字符串(自动截取第一个字符)
f: Float 浮点 -- 对应位置参数必须是数字型


转义字符
\a	警告字符，通常为ASCII的BEL字符
\b	后退
\c	抑制（不显示）输出结果中任何结尾的换行字符（只在%b格式指示符控制下的参数字符串中有效），而且，任何留在参数里的字符、任何接下来的参数以及任何留在格式字符串中的字符，都被忽略
\f	换页（formfeed）
\n	换行
\r	回车（Carriage return）
\t	水平制表符
\v	垂直制表符
\\	一个字面上的反斜杠字符
\ddd	表示1到3位数八进制值的字符。仅在格式字符串中有效
\0ddd	表示1到3位的八进制值字符


#	流程控制
流程控制不可为空

if 语句语法格式：
if condition
then
    command1 
    command2
    ...
    commandN 
fi
if [ $(ps -ef | grep -c "ssh") -gt 1 ]; then echo "true"; fi

if else 语法格式：
if condition
then
    command1 
    command2
    ...
    commandN
else
    command
fi


if else-if else 语法格式：
if condition1
then
    command1
elif condition2 
then 
    command2
else
    commandN
fi


for 循环
for var in item1 item2 ... itemN
do
    command1
    command2
    ...
    commandN
done
for var in item1 item2 ... itemN; do command1; command2… done;

C风格
for((assignment;condition:next));do
    command_1;
    command_2;
    commond_..;
done;
通常情况下 shell 变量调用需要加 $,但是 for 的 (()) 中不需要,下面来看一个例子：
#!/bin/bash
for((i=1;i<=5;i++));do
    echo "这是第 $i 次调用";
done;


while 语句
while condition
do
    command
done


until 循环
until 循环执行一系列命令直至条件为 true 时停止。
until condition
do
    command
done


case语句为多选择语句
case 值 in
模式1)
    command1
    command2
    ...
    commandN
    ;;
模式2）
    command1
    command2
    ...
    commandN
    ;;
esac

echo '输入 1 到 4 之间的数字:'
echo '你输入的数字为:'
read aNum
case $aNum in
    1)  echo '你选择了 1'
    ;;
    2)  echo '你选择了 2'
    ;;
    3)  echo '你选择了 3'
    ;;
    4)  echo '你选择了 4'
    ;;
    *)  echo '你没有输入 1 到 4 之间的数字'
    ;;
esac


break
break命令允许跳出所有循环（终止执行后面的所有循环）。

continue
continue命令仅跳出当前循环。





#	函数
[ function ] funname [()]

{

    action;

    [return int;]

}
1、可以带function fun() 定义，也可以直接fun() 定义,不带任何参数。
2、参数返回，可以显示加：return 返回，如果不加，将以最后一条命令运行结果，作为返回值。 return后跟数值n(0-255)
3、函数返回值在调用该函数后通过 $? 来获得。
4、所有函数在使用前必须定义。这意味着必须将函数放在脚本开始部分，直至shell解释器首次发现它时，才可以使用。调用函数仅使用其函数名即可。
funWithReturn(){
    echo "这个函数会对输入的两个数字进行相加运算..."
    echo "输入第一个数字: "
    read aNum
    echo "输入第二个数字: "
    read anotherNum
    echo "两个数字分别为 $aNum 和 $anotherNum !"
    return $(($aNum+$anotherNum))
}
funWithReturn
echo "输入的两个数字之和为 $? !"

5、在Shell中，调用函数时可以向其传递参数。在函数体内部，通过 $n 的形式来获取参数的值
注意，$10 不能获取第十个参数，获取第十个参数需要${10}。当n>=10时，需要使用${n}来获取参数。
funWithParam(){
    echo "第一个参数为 $1 !"
    echo "第二个参数为 $2 !"
    echo "第十个参数为 $10 !"
    echo "第十个参数为 ${10} !"
    echo "第十一个参数为 ${11} !"
    echo "参数总数有 $# 个!"
    echo "作为一个字符串输出所有参数 $* !"
}
funWithParam 1 2 3 4 5 6 7 8 9 34 73


$#	传递到脚本的参数个数
$*	以一个单字符串显示所有向脚本传递的参数
$$	脚本运行的当前进程ID号
$!	后台运行的最后一个进程的ID号
$@	与$*相同，但是使用时加引号，并在引号中返回每个参数。
$-	显示Shell使用的当前选项，与set命令功能相同。
$?	显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。


#	外部脚本
. filename   # 注意点号(.)和文件名中间有一空格
或
source filename

#test1.sh
#!/bin/bash
url="http://www.runoob.com"

#test2.sh
#!/bin/bash
#使用 . 号来引用./test1.sh文件，被包含的文件 test1.sh 不需要可执行权限。
. ./test1.sh
# 或者使用以下包含文件代码
# source ./test1.sh
echo "菜鸟教程官网地址：$url"


#	输入/输出重定向
command > file	将输出重定向到 file。
command < file	将输入重定向到 file。
command >> file	将输出以追加的方式重定向到 file。
n > file	将文件描述符为 n 的文件重定向到 file。
n >> file	将文件描述符为 n 的文件以追加的方式重定向到 file。
n >& m	将输出文件 m 和 n 合并。
n <& m	将输入文件 m 和 n 合并。
<< tag	将开始标记 tag 和结束标记 tag 之间的内容作为输入。
文件描述符 0 通常是标准输入（STDIN），1 是标准输出（STDOUT），2 是标准错误输出（STDERR）

/dev/null 文件
如果希望执行某个命令，但又不希望在屏幕上显示输出结果，那么可以将输出重定向到 /dev/null：
$ command > /dev/null
























