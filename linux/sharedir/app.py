from flask import Flask, render_template, request, make_response, send_file, send_from_directory
from flask import redirect, url_for
import os, time
import markdown
from markdown.extensions import Extension

app = Flask(__name__)
#BASE_URL = 'G:\\share\\share'
BASE_URL = '/var/sharedir'


@app.route('/', methods=['GET', 'POST'])
def index():
    return redirect(url_for('source'))


@app.route('/share', methods=['GET', 'POST'])
@app.route('/share/', methods=['GET', 'POST'])
def source():
    if request.method == 'GET':
        return list_dirs(BASE_URL)
    elif request.method == 'POST':
        return upload_file(BASE_URL)


@app.route('/share/<path:dir>', methods=['GET', 'POST'])
@app.route('/share/<path:dir>/', methods=['GET', 'POST'])
def child(dir):
    if len(dir) >= 1 and dir[-1] == '/':
        dir = dir[:-1]
    if len(dir) >= 1 and dir[0] == '/':
        dir = dir[1:]

    dir = dir.replace('/', os.path.sep)
    dir = os.path.join(BASE_URL, dir)

    if request.method == 'GET':
        if not os.path.exists(dir):
            return '目录不存在'

        if os.path.isfile(dir):
            if request.args.get('download', '') == 'true':
                return open_file(dir, True)
            if os.path.splitext(dir)[1] == '.md':
                return convert_md(dir)
            else:
                return open_file(dir)
        else:
            return list_dirs(dir)

    elif request.method == 'POST':
        return upload_file(dir)


def list_dirs(dir_completed):
    print('list      ', dir_completed)
    dirs = os.listdir(dir_completed)
    dir_infos = []
    for d in dirs:
        f = os.path.join(dir_completed, d)
        info = FileInfo(d, os.path.isdir(f), os.path.getmtime(f), os.path.getsize(f))
        dir_infos.append(info)
    dir_infos.sort(key=lambda info: (info.modify_time * 2 if info.is_dir else info.modify_time), reverse=True)
    return render_template('listdir.html', dir_infos=dir_infos)


class FileInfo(object):
    def __init__(self, name, is_dir, modify_time, size):
        self.name = name
        self.is_dir = is_dir
        self.modify_time = modify_time
        self.size = size
        self.display = name + ('/' if is_dir else '')
        self.openable = False
        if not is_dir and OPEN_FILE.__contains__(os.path.splitext(name)[1]):
            self.openable = True

    def get_size_str(self):
        l = self.size
        if l == 0:
            return ''
        if l < 1024:
            return str(l) + 'B'
        l //= 1024
        if l < 1024:
            return str(l) + 'K'
        l //= 1024
        if l < 1024:
            return str(l) + 'M'
        l //= 1024
        if l < 1024:
            return str(l) + 'G'

    def get_modify_time_str(self):
        ts = time.localtime(self.modify_time)
        return '%04d/%02d/%02d  %02d:%02d:%02d' % (ts[0], ts[1], ts[2], ts[3], ts[4], ts[5],)


def upload_file(dir_completed):
    f = request.files['file']
    if f.filename == None or f.filename == '':
        return list_dirs(dir_completed)
    if not os.path.exists(dir_completed):
        os.mkdir(dir_completed)
    save_dir = os.path.join(dir_completed, f.filename)
    f.save(save_dir)
    print('upload    ', save_dir)
    return list_dirs(dir_completed)


OPEN_FILE = ('.txt','.pdf', '.doc', '.docx','.ppt','.pptx', '.png', '.jpg', '.md', '.html')


def open_file(file, just_download=False):
    print('open      ', file)
    response = make_response(send_from_directory(
        os.path.split(file)[0], os.path.split(file)[1], as_attachment=True))
    type = 'inline'
    if just_download:
        type = 'attachment'
    elif OPEN_FILE.__contains__(os.path.splitext(file)[1]):
        type = 'inline'
    else:
        type = 'attachment'
    response.headers['Content-Disposition'] = type + '; filename={}'.format(
        os.path.split(file)[1].encode().decode('latin-1'))
    return response


BASE_HTML = '''
<html lang="zh-cn">
<head>
<meta content="text/html; charset=utf-8" http-equiv="content-type" />
<link rel="stylesheet" src="/static/css-github.css" />
</head>
<body>
%s
</body>
</html>
'''


def convert_md(file):
    print('openMd   ', file)
    with open(file, 'r', encoding='utf-8', errors='ignore') as md_f:
        return BASE_HTML % markdown.markdown(md_f.read(), extensions=['markdown.extensions.extra'])


if __name__ == '__main__':
    app.run(host='0.0.0.0',port='3346')
