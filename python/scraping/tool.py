#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import re

export_ip_server = 'http://2017.ip138.com/ic.asp'


def get_export_ip_server():
    r = requests.get('http://www.ip138.com/')
    soup = BeautifulSoup(r.text, 'html.parser')
    return soup.iframe['src']


def get_export_ip(proxies=None, http=None, https=None, ):
    global export_ip_server
    if http is not None:
        if proxies is None:
            proxies = {}
        proxies['http'] = http
    if https is not None:
        if proxies is None:
            proxies = {}
        proxies['https'] = https
    try:
        r = requests.get(export_ip_server, timeout=10, proxies=proxies)
        if r.status_code != 200:
            export_ip_server = get_export_ip_server()
            r = requests.get(export_ip_server, timeout=10)
    except BaseException as e:
        export_ip_server = get_export_ip_server()
        r = requests.get(export_ip_server, timeout=10)
    soup = BeautifulSoup(r.content.decode('gb2312'), 'html.parser')
    return re.search('[0-9]+.[0-9]+.[0-9]+.[0-9]+', soup.find('center').string).group()


def complete_url(base, src, current):
    if src.startswith('/'):
        src = base + src
    elif src.startswith('http'):
        src = src
    else:
        src = current + '/' + src
    if base not in src:
        return None
    return src


def save_img(url, filename):
    r = requests.get(url, stream=True)
    with open(filename, 'wb') as fd:
        for chunk in r.iter_content(2048):
            fd.write(chunk)
