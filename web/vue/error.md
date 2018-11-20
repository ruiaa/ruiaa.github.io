清除缓存

Uncaught Error: Module parse failed: /Users/**/Desktop/vue2/node_modules/.1.0.0-rc.5@element-ui/lib/theme-default/index.css Unexpected character '@' (1:0)
You may need an appropriate loader to handle this file type.

官网文档又有坑了，安装教程也不跟我们说这一步，当我们都是高手了...
报错是由于我们引入了index.css这个 CSS 文件，但是 webpack 打包的时候无法识别并转换成 js，所以就需要配置才能读取 css 和字体文件，运行命令安装下面三个东西(如果之前安装过就不需要了)

npm install style-loader --save-dev
npm install css-loader --save-dev
npm install file-loader --save-dev

      {
        test: /\.eot/,
        loader: 'file-loader?prefix=font/'
      },
      {
        test: /\.woff/,
        loader: 'file-loader?prefix=font/&limit=10000&mimetype=application/font-woff'
      },
      {
        test: /\.ttf/,
        loader: 'file-loader?prefix=font/'
      },
      {
        test: /\.svg/,
        loader: 'file-loader?prefix=font/'
      }
      
      
      