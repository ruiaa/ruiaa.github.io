#	数据库

##	SQLite3

    #
    # 连接
    # 导入SQLite驱动:
    >>> import sqlite3
    # 连接到SQLite数据库
    # 数据库文件是test.db
    # 如果文件不存在，会自动在当前目录创建:
    >>> conn = sqlite3.connect('test.db')
    # 创建一个Cursor:
    >>> cursor = conn.cursor()
    # 执行一条SQL语句，创建user表:
    >>> cursor.execute('create table user (id varchar(20) primary key, name varchar(20))')
    <sqlite3.Cursor object at 0x10f8aa260>
    # 继续执行一条SQL语句，插入一条记录:
    >>> cursor.execute('insert into user (id, name) values (\'1\', \'Michael\')')
    <sqlite3.Cursor object at 0x10f8aa260>
    # 通过rowcount获得插入的行数:
    >>> cursor.rowcount
    1
    # 关闭Cursor:
    >>> cursor.close()
    # 提交事务:
    >>> conn.commit()
    # 关闭Connection:
    >>> conn.close()
    
    #
    # select，insert，update，delete
    >>> conn = sqlite3.connect('test.db')
    >>> cursor = conn.cursor()
    # 执行查询语句:
    >>> cursor.execute('select * from user where id=?', ('1',))
    <sqlite3.Cursor object at 0x10f8aa340>
    # 获得查询结果集:
    >>> values = cursor.fetchall()
    >>> values
    [('1', 'Michael')]
    >>> cursor.close()
    >>> conn.close()
    
    #
    # 如果SQL语句带有参数，那么需要把参数按照位置传递给execute()方法，
    # 有几个?占位符就必须对应几个参数，例如：
    cursor.execute('select * from user where name=? and pwd=?', ('abc', 'password'))




#	ORM SQLAlchemy

	$ pip install sqlalchemy


    # 导入:
    from sqlalchemy import Column, String, create_engine
    from sqlalchemy.orm import sessionmaker
    from sqlalchemy.ext.declarative import declarative_base
    
    # 创建对象的基类:
    Base = declarative_base()
    
    # 定义User对象:
    class User(Base):
        # 表的名字:
        __tablename__ = 'user'
    
        # 表的结构:
        id = Column(String(20), primary_key=True)
        name = Column(String(20))
    
    # 初始化数据库连接:
    #			'数据库类型+数据库驱动名称://用户名:口令@机器地址:端口号/数据库名'
    engine = create_engine('mysql+mysqlconnector://root:password@localhost:3306/test')
    # 创建DBSession类型:
    DBSession = sessionmaker(bind=engine)
    
    
    # 创建session对象:
    session = DBSession()
    # 创建新User对象:
    new_user = User(id='5', name='Bob')
    # 添加到session:
    session.add(new_user)
    # 提交即保存到数据库:
    session.commit()
    # 关闭session:
    session.close()
    
    # 创建Session:
    session = DBSession()
    # 创建Query查询，filter是where条件，最后调用one()返回唯一行，如果调用all()则返回所有行:
    user = session.query(User).filter(User.id=='5').one()
    # 打印类型和对象的name属性:
    print('type:', type(user))
    print('name:', user.name)
    # 关闭Session:
    session.close()
    
    
    
    
    #
    #
    # 外键
    class User(Base):
        __tablename__ = 'user'
    
        id = Column(String(20), primary_key=True)
        name = Column(String(20))
        # 一对多:
        books = relationship('Book')
    
    class Book(Base):
        __tablename__ = 'book'
    
        id = Column(String(20), primary_key=True)
        name = Column(String(20))
        # “多”的一方的book表是通过外键关联到user表的:
        user_id = Column(String(20), ForeignKey('user.id'))



#   py-mysql

    import pymysql

    # 打开数据库连接
    db = pymysql.connect("localhost","testuser","test123","TESTDB" )

    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()

    # 使用 execute()  方法执行 SQL 查询
    cursor.execute("SELECT VERSION()")

    # 使用 fetchone() 方法获取单条数据.
    data = cursor.fetchone()

    print ("Database version : %s " % data)

    # 关闭数据库连接
    db.close()


















