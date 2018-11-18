#   python3
+   准备编译环境
    +   yum groupinstall 'Development Tools'
    +   yum install zlib-devel bzip2-devel openssl-devel ncurese-devel
        +   yum install openssl-devel bzip2-devel expat-devel gdbm-devel readline-devel sqlite-devel
+   下载安装
    +   wget https://www.python.org/ftp/python/3.6.3/Python-3.6.3.tar.xz
    +   tar Jxvf Python-3.6.3.tar.xz -C /usr/local
    +   cd Python-3.6.3  ??yum install openssl-devel 
    +   ./configure --with-ssl --prefix=/usr/local/python3
    +   make && make install
+   软连接
    +   ln -s /usr/local/python3/bin/python3.6 /usr/bin/python3
    +   ln -s /usr/local/python3/bin/pip3 /usr/bin/pip3
+   pip3 install sqlalchemy flask flask_sqlalchemy sqlalchemy apscheduler pymysql gunicorn
+   ssl问题
    
        import ssl
        ./configure --with-ssl
        
        openssl
        tar zxf openssl-0.9.8g.tar.gz
        cd openssl-0.9.8g
        ./config
        make
        make install
        
        
        /usr/lib64/openssl
            lib4758cca.so  libcapi.so    libgmp.so      libsureware.so
            libaep.so      libchil.so    libnuron.so    libubsec.so
            libatalla.so   libcswift.so  libpadlock.so
        
        /usr/include/openssl
            opensslconf.h        
            opensslconf-x86_64.h 
            opensslv.h           
            
        /usr/bin/openssl
        
        pip install pyopenssl
        
        
+   pip源
    
        ~/.pip/pip.conf
        
[global]
index-url = http://mirrors.aliyun.com/pypi/simple/

[install]
trusted-host=mirrors.aliyun.com