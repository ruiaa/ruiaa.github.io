Classification 分类
Regression 回归
Clustering 非监督分类
Dimensionality reduction 数据降维
Model Selection 模型选择
Preprocessing 数据预处理

分类，回归，聚类，降维

分类和回归是监督式学习，即每个数据对应一个 label。 
聚类是非监督式学习，即没有 label。
降维，当数据集有很多很多属性的时候，可以通过 降维 算法把属性归纳起来。
	例如 20 个属性只变成 2 个，注意，这不是挑出 2 个，而是压缩成为 2 个，它们集合了 20 个属性的所有特征，相当于把重要的信息提取的更好，不重要的信息就不要了。


#   Preprocessing
StandardScaler
无量纲化
标准化，基于特征矩阵的列，将特征值转换至服从标准正态分布


MinMaxScaler
无量纲化
区间缩放，基于最大最小值，将特征值转换到[0, 1]区间上


Normalizer
归一化
基于特征矩阵的行，将样本向量转换为“单位向量”


Binarizer
二值化
基于给定阈值，将定量特征按阈值划分


OneHotEncoder
哑编码
将定性数据编码为定量数据


Imputer
缺失值计算
计算缺失值，缺失值可填充为均值等


PolynomialFeatures
多项式数据转换
多项式数据转换


FunctionTransformer
自定义单元数据转换
使用单变元的函数来转换数据函数来转换数据




#   LabelEncoder  文本标签 --> 数值

LabelEncoder是用来对分类型特征值进行编码，即对不连续的数值或文本进行编码。其中包含以下常用方法：

fit(y) ：fit可看做一本空字典，y可看作要塞到字典中的词。 
fit_transform(y)：相当于先进行fit再进行transform，即把y塞到字典中去以后再进行transform得到索引值。 
inverse_transform(y)：根据索引值y获得原始数据。 
transform(y) ：将y转变成索引值。


如：

>>> from sklearn import preprocessing
>>> le = preprocessing.LabelEncoder()
>>> le.fit([1, 2, 2, 6])
LabelEncoder()
>>> le.classes_
array([1, 2, 6])
>>> le.transform([1, 1, 2, 6]) 
array([0, 0, 1, 2]...)
>>> le.inverse_transform([0, 0, 1, 2])
array([1, 1, 2, 6])

>>> le = preprocessing.LabelEncoder()
>>> le.fit(["paris", "paris", "tokyo", "amsterdam"])
LabelEncoder()
>>> list(le.classes_)
['amsterdam', 'paris', 'tokyo']
>>> le.transform(["tokyo", "tokyo", "paris"]) 
array([2, 2, 1]...)
>>> list(le.inverse_transform([2, 2, 1]))
['tokyo', 'tokyo', 'paris']