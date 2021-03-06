cd ~
yum groupinstall 'Development Tools'
yum install zlib-devel bzip2-devel openssl-devel ncurese-devel
cd ~
wget https://www.python.org/ftp/python/3.6.2/Python-3.6.2.tar.xz
tar Jxvf Python-3.6.2.tar.xz
cd Python-3.6.2
./configure --prefix=/usr/local/python3
make && make install

ln -s /usr/local/python3/bin/python3.6 /usr/bin/python3
ln -s /usr/local/python3/bin/pip3 /usr/bin/pip3