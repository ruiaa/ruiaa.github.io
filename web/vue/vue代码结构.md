
#   代码结构

        |-- src                              // 源码目录
        |   |-- components                   // vue所有组件
        |   |-- router                       // vue的路由管理
        |   |-- App.vue                      // 页面入口文件
        |   |-- main.js                      // 程序入口文件，加载各种公共组件
        |-- static                           // 静态文件，比如一些图片，json数据等
        |-- index.html                       // 访问的页面
.

        request
           |
           |
           |                    App.vue
           ↓                  ↙
       index.html  ← (main.js)   
           |                  ↖router/index.js    ←←   components + router
           |
           |       
           ↓
       单页面应用

+   index.html：初次访问的页面，提供注入点\<div id="app">
+   App.vue：首页框架，提供\<router-view>（和导航\<router-link>）
+   router/index.js：路由文件，url-->components
+   src/main.js：程序入口文件，创建挂载根实例并注入路由，加载各种公共组件




#   index.html 访问入口

    <!DOCTYPE html>
    <html>
      <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>subscription</title>
      </head>
      <body>
        <div id="app"></div>
        <!-- built files will be auto injected -->
      </body>
    </html>

#   src/main.js  程序入口
    import Vue from 'vue';
    import App from './App.vue';
    import router from './router/index.js';
    
    //创建和挂载根实例。通过 router 配置参数注入路由，从而让整个应用都有路由功能
    new Vue({
      el: "#app",
      router,
      components: {App},
      template: '<App/>',
    });
    
#   src/app.vue  页面入口
    <template>
      <div id="app">
        <img src="./assets/logo.png">
        <router-view></router-view>
      </div>
    </template>
    <script>
    export default {
      name: 'app'
    }
    </script>
    <style>
    #app {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      margin-top: 60px;
    }
    </style>
    
    
    <template></template> 标签包裹的内容：这是模板的HTMLDom结构 
    <script></script>     标签包括的js内容：你可以在这里写一些页面的js的逻辑代码。 
    <style></style>       标签包裹的css内容：页面需要的CSS样式。

#   src/router/index.js 总路由
    import Vue from 'vue'
    import Router from 'vue-router'
    import Hello from '@/components/Hello'
    
    Vue.use(Router)
    
    export default new Router({
      routes: [//配置路由
        {
          path: '/',        //访问路径
          name: 'Hello',    //路由名称
          component: Hello  //路由需要的组件（驼峰式命名）
        },
        {
          path:'*', redirect:'/'
        }
      ]









































