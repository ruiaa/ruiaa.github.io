#!/usr/bin/env python3
# -*- coding: utf-8 -*-


import logging; logging.basicConfig(level=logging.INFO)

import asyncio, os, json, time
from datetime import datetime

from aiohttp import web

def index(request):
    return web.Response(body=b'<h1>Awesome</h1>',content_type='text/html', charset='UTF-8')

@asyncio.coroutine
def init(loop,host):
    app = web.Application(loop=loop)
    app.router.add_route('GET', '/', index)
    srv = yield from loop.create_server(app.make_handler(), host, 9900)
    logging.info('server started at http://%s:9000...' % host)
    return srv

host=input('input host  ')
loop = asyncio.get_event_loop()
loop.run_until_complete(init(loop,host)) #'127.0.0.1'
loop.run_forever()