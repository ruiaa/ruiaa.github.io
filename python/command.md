#	pyinstaller
pyinstaller --version
pyinstaller : 打包可执行文件的主要命令，详细用法下面会介绍。
pyi-archive_viewer : 查看可执行包里面的文件列表。
pyi-bindepend : 查看可执行文件依赖的动态库（.so或.dll文件）


pyinstaller : 打包可执行文件的主要命令
pyinstaller mycript.py  输出目录build和dist，dist里面文件就是可以发布的可执行文件
pyinstaller -F mycript.py  dist下面只有一个可执行文件


在执行pyInstaller命令的时候，会在和脚本相同目录下，生成一个.spec文件，该文件会告诉pyinstaller如何处理你的所有脚本，同时包含了命令选项。一般我们不用去理会这个文件，若需要打包数据文件，或者给打包的二进制增加一些Python的运行时选项时...一些高级打包选项时，需要手动编辑.spec文件。可以使用：

pyi-makespec optionsscript [script ...]

创建一个.spec文件，对于手动编辑的.spec文件，我们可以使用下面任意一条命令：

pyinstaller specfile

pyi-build specfile




pyinstaller -F -w -p D:\tmp\core-python\libs -i d:\tmp\main.ico main.py
-F 表示生成单个可执行文件
-w 表示去掉控制台窗口，这在GUI界面时非常有用
-p 表示你自己自定义需要加载的类路径，一般情况下用不到
-i 表示可执行文件的图标








