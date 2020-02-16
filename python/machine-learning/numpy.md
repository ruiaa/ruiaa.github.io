#   N 维数组对象 ndarray
ndarray 对象是用于存放同类型元素的多维数组。
ndarray 中的每个元素在内存中都有相同存储大小的区域。
ndarray 内部由以下内容组成：
    一个指向数据（内存或内存映射文件中的一块数据）的指针。
    数据类型或 dtype，描述在数组中的固定大小值的格子。
    一个表示数组形状（shape）的元组，表示各维度大小的元组。
    一个跨度元组（stride），其中的整数指的是为了前进到当前维度下一个元素需要"跨过"的字节数。

numpy.array(object, dtype = None, copy = True, order = None, subok = False, ndmin = 0)
参数说明：
object  数组或嵌套的数列
dtype   数组元素的数据类型，可选
copy    对象是否需要复制，可选
order   创建数组的样式，C为行方向，F为列方向，A为任意方向（默认）
subok   默认返回一个与基类类型一致的数组
ndmin   指定生成数组的最小维度

import numpy as np 
a = np.array([[1,  2],  [3,  4]])  
print (a)
输出结果如下：
[[1, 2] 
 [3, 4]]


 #  数组属性
 NumPy 数组的维数称为秩（rank），一维数组的秩为 1，二维数组的秩为 2，以此类推。

在 NumPy中，每一个线性的数组称为是一个轴（axis），也就是维度（dimensions）。比如说，二维数组相当于是两个一维数组，其中第一个一维数组中每个元素又是一个一维数组。所以一维数组就是 NumPy 中的轴（axis），第一个轴相当于是底层数组，第二个轴是底层数组里的数组。而轴的数量——秩，就是数组的维数。

很多时候可以声明 axis。axis=0，表示沿着第 0 轴进行操作，即对每一列进行操作；axis=1，表示沿着第1轴进行操作，即对每一行进行操作。

NumPy 的数组中比较重要 ndarray 对象属性有：

属性  说明
ndarray.ndim    秩，即轴的数量或维度的数量
ndarray.shape   数组的维度，对于矩阵，n行m列 (n,m)
ndarray.size    数组元素的总个数，相当于 .shape 中 n*m 的值*
ndarray.dtype   ndarray 对象的元素类型
ndarray.itemsize    ndarray 对象中每个元素的大小，以字节为单位
ndarray.flags   ndarray 对象的内存信息
ndarray.real    ndarray元素的实部
ndarray.imag    ndarray 元素的虚部
ndarray.data    包含实际数组元素的缓冲区，由于一般通过数组的索引获取元素，所以通常不需要使用这个属性。



#   创建数组
numpy.empty(shape, dtype = float, order = 'C')
    创建一个指定形状（shape）、数据类型（dtype）且未初始化的数组
    order : 有"C"和"F"两个选项,分别代表，行优先和列优先，在计算机内存中的存储元素的顺序。
    np.empty([3,2], dtype = int) : [[ 6917529027641081856  5764616291768666155]
                                    [ 6917529027641081859 -5764598754299804209]
                                    [          4497473538      844429428932120]]

numpy.zeros(shape, dtype = float, order = 'C')
    创建指定大小的数组，数组元素以 0 来填充
    np.zeros((5,), dtype = np.int) : [0 0 0 0 0]
    np.zeros((2,2), dtype = [('x', 'i4'), ('y', 'i4')]) : 
        自定义类型为('i4','i4')
            [[(0, 0) (0, 0)]
             [(0, 0) (0, 0)]]

numpy.ones(shape, dtype = None, order = 'C')
    创建指定形状的数组，数组元素以 1 来填充


numpy.arange(start, stop, step, dtype)
    根据 start 与 stop 指定的范围以及 step 设定的步长，生成一个 ndarray
    np.arange(5) : [0  1  2  3  4]
    np.arange(10,20,2) : [10  12  14  16  18]

np.linspace(start, stop, num=50, endpoint=True, retstep=False, dtype=None)
    创建一个一维数组，数组是一个等差数列构成的
    num : 要生成的等步长的样本数量，默认为50
    endpoint : 该值为 ture 时，数列中中包含stop值，反之不包含，默认是True。
    retstep : 如果为 True 时，生成的数组中会显示间距，反之不显示。

np.logspace(start, stop, num=50, endpoint=True, base=10.0, dtype=None)
    等比数列
    start : 序列的起始值为：base ** start
    stop: 序列的终止值为：base ** stop。如果endpoint为true，该值包含于数列中
    base : 对数 log 的底数。





#   切片和索引
ndarray 数组可以基于 0 - n 的下标进行索引，
切片对象可以通过内置的 slice 函数，并设置 start, stop 及 step 参数进行，从原数组中切割出一个新数组。

np.arange(10)[slice(2,7,2)]
np.arange(10)[[2:7:2]]
    从索引 2 开始到索引 7 停止，间隔为2
    [2, 4, 6]
冒号 : 
    如果只放置一个参数，如 [2]，将返回与该索引相对应的单个元素。
    如果为 [2:]，表示从该索引开始以后的所有项都将被提取。
    如果使用了两个参数，如 [2:7]，那么则提取两个索引(不包括停止索引)之间的项。
省略号 …
    选择元组的长度与数组的维度相同。 如果在行位置使用省略号，它将返回包含行中元素的 ndarray
    a[...,1]  # 第2列元素
    a[1,...]  # 第2行元素
    a[...,1:} # 第2列及剩下的所有元素









https://www.apachecn.org/numpy-ref-zh/generated/numpy.savetxt.html
numpy.savetxt(fname, X, fmt='%.18e', delimiter=' ', newline='\n', header='', footer='', comments='# ')[source]
将数组保存到文本文件。

参数： 
fname：文件名或文件句柄

如果文件名以.gz结尾，则文件将自动以压缩gzip格式保存。loadtxt透明地理解gzip压缩的文件。

X：array_like

要保存到文本文件的数据。

fmt：str或strs序列，可选

单一格式（％10.5f），一系列格式或多格式字符串，例如。 '迭代％d - ％10.5f'，在这种情况下忽略分隔符。对于复杂的X，fmt的合法选项为：

单个说明符，fmt ='％。4e'，导致数字格式化
像'（％s +％sj）'％（fmt，fmt）
指定每个实部和虚部的完整字符串，例如
3列的'％.4e％+ .4j％.4e％+ .4j％.4e％+。4j'
一个说明符列表，每列一个 - 在这种情况下，真正的
和虚部必须具有单独的指示符，例如。 2列的['％。3e +％.3ej'，'（％.15e％+。15ej）']
分隔符：str，可选

分隔列的字符串或字符。

换行符：str，可选

字符串或字符分隔线。

版本1.5.0中的新功能。

header：str，可选

将写入文件开头的字符串。

版本1.7.0中的新功能。

footer：str，可选

将写入文件末尾的字符串。

版本1.7.0中的新功能。

注释：str，可选

将添加到header和footer字符串的字符串，以将它们标记为注释。默认值：'＃'，正如例如numpy.loadtxt。

版本1.7.0中的新功能。




