#!/bin/sh

yum -y install make zlib zlib-devel gcc-c++ libtool openssl openssl-devel

cd ~
wget http://downloads.sourceforge.net/project/pcre/pcre/8.41/pcre-8.41.tar.gz
tar -zxvf pcre-8.41.tar.gz -C /usr/local
mv /usr/local/pcre-8.41 /usr/local/pcre
cd /usr/local/pcre
./configure --prefix=/usr/local/pcre
make && make install

cd ~
wget http://zlib.net/zlib-1.2.11.tar.gz
tar -zxvf zlib-1.2.11.tar.gz -C /usr/local
mv /usr/local/zlib-1.2.11 /usr/local/zlib
cd /usr/local/zlib
./configure --prefix=/usr/local/zlib
make && make install

cd ~
wget http://www.openssl.org/source/openssl-1.0.1j.tar.gz
tar -zxvf openssl-1.0.1j.tar.gz -C /usr/local
mv /usr/local/openssl-1.0.1j /usr/local/openssl
cd /usr/local/openssl
./config --prefix=/usr/local/openssl
make && make install

cd ~
wget http://nginx.org/download/nginx-1.13.5.tar.gz
tar -zxvf nginx-1.13.5.tar.gz
cd nginx-1.13.5  
./configure --prefix=/usr/local/nginx --with-pcre=/usr/local/pcre --with-zlib=/usr/local/zlib
make && make install