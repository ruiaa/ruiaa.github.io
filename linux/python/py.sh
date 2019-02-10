#! /bin/bash
yum groupinstall 'Development Tools' &&
yum install openssl-devel zlib-devel bzip2-devel ncurese-devel expat-devel gdbm-devel readline-devel sqlite-devel &&
cd ~ &&
wget https://www.python.org/ftp/python/3.6.3/Python-3.6.3.tar.xz &&
tar Jxvf Python-3.6.3.tar.xz &&
cd Python-3.6.3 &&
./configure --with-ssl --prefix=/usr/local/python3 &&
make &&
make install &&
ln -s /usr/local/python3/bin/python3.6 /usr/bin/python3 &&
ln -s /usr/local/python3/bin/pip3 /usr/bin/pip3