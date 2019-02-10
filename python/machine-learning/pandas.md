series 是一种一维的数据类型，其中的每个元素都有各自的标签。如果你之前看过这个系列关于 Numpy 的推文，你可以把它当作一个由带标签的元素组成的 numpy 数组。标签可以是数字或者字符。

dataframe 是一个二维的、表格型的数据结构。Pandas 的 dataframe 可以储存许多不同类型的数据，并且每个轴都有标签。你可以把它当作一个 series 的字典。

#   导入，导出
read_csv，to_csv
df = pd.read_csv('uk_rain_2014.csv',header=0,index_col=0,encoding='utf-8')

#   concat(join)
pd.concat(objs, axis=0, join='outer', join_axes=None, ignore_index=False,
       keys=None, levels=None, names=None, verify_integrity=False)

参数说明 
objs: series，dataframe或者是panel构成的序列lsit 
axis： 需要合并链接的轴，0是行，1是列 
join：连接的方式 inner，或者outer

#   DataFrame.shift(periods=1, freq=None, axis=0)
periods：类型为int，表示移动的幅度，可以是正数，也可以是负数，默认值是1,1就表示移动一次，注意这里移动的都是数据，而索引是不移动的，移动之后没有对应值的，就赋值为NaN。
freq： DateOffset, timedelta, or time rule string，可选参数，默认值为None，只适用于时间序列，如果这个参数存在，那么会按照参数值移动时间索引，而数据值没有发生变化。
axis：{0, 1, ‘index’, ‘columns’}，表示移动的方向，如果是0或者’index’表示上下移动，如果是1或者’columns’，则会左右移动。