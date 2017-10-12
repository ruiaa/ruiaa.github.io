#   flask sqlalchemy
##  初始化
    app = Flask(__name__)
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://user:password@host:3306/dbname?charset=utf8'
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True
    
    db = SQLAlchemy()
    db.app = app
    db.init_app(app)
##  模型定义
    class Role(db.Model):
        __tablename__ = 'roles'
        id = db.Column(db.Integer, nullable=False, primary_key=True, autoincrement=True)
        name = db.Column(db.String(16), nullable=False, server_default='', unique=True)
        def __repr__(self):
            return '<Role %r>' % self.name
    class User(db.Model):
        __tablename__ = 'users'
        id = db.Column(db.Integer, nullable=False, primary_key=True, autoincrement=True)
        username = db.Column(db.String(32), nullable=False, unique=True, server_default='', index=True)
        role_id = db.Column(db.Integer, nullable=False, server_default='0')
        def __repr__(self):
            return '<User %r,Role id %r>' %(self.username,self.role_id)
##  操作
+   创建所有表  db.create_all()
+   删除所有表  db.drop_all()
+   插入

        # 插入单行
        python
        >>> from hello import db,Role,User
        >>> db.session.add(Role(name='Admin'))
        >>> db.session.commit()
        >>> db.session.add(Role(name='Moderator'))
        >>> db.session.add(Role(name='User'))
        >>> db.session.commit()
        # 插入多行
        python
        >>> from hello import db,Role,User
        >>> db.session.add_all([User(username='john',role_id=1),User(username='susan',role_id=3),User(username='david',role_id=3)])
        >>> db.session.commit()
+   更新

        python
        >>> from hello import db,Role,User
        >>> admin = Role.query.filter_by(name='Admin').first()
        >>> admin.name='Administrator'
        >>> db.session.commit()
+   删除

        python hello.py shell
        >>> from hello import db,Role,User
        >>> mod = Role.query.filter_by(name='Moderator').first()
        >>> db.session.delete(mod)
        >>> db.session.commit()
+   查询
    +   查询表中全部数据 User.query.all()
    +   按照一个条件过滤数据记录(where)  User.query.filter_by(role_id=3).first()
    +   按照两个条件过滤数据记录(where and) User.query.filter_by(role_id=3,username='susan').all()
    +   聚合(count)  User.query.filter_by(role_id=3).count()
    +   求和(sum) User.query.with_entities(func.sum(User.id)).all()
    +   平均数(avg) User.query.with_entities(func.avg(User.id)).all()
    +   排序(order by) User.query.order_by(User.role_id.desc()).all()
    +   分组(group by) User.query.group_by(User.role_id).all()
    +   限制(limit)  User.query.limit(1).offset(2).all()
+   将Flask-SQLAlchemy的查询语句转换为SQL str(User.query.limit(1).offset(2))
    