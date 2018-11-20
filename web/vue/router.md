#   安装依赖
npm install vue-router --save-dev
    
#   工作流程    
    
    <!-- 使用 router-link 组件来导航. -->
    <!-- 通过传入 `to` 属性指定链接. -->
    <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
    <router-link to="/bar">Go to Bar</router-link>
    <!-- 路由出口 -->
    <!-- 路由匹配到的组件将渲染在这里 -->
    <router-view></router-view>

    // 2、定义路由
    // 每个路由应该映射一个组件
    // 谁先定义的，谁的优先级就最高。
    import Vue from 'vue'
    import Router from 'vue-router'
    import Hello from '@/components/Hello'
    Vue.use(Router);
    const routes = [
      { 
        path: '/foo', component: Foo 
      },
      {
        path: '/menu/:menu', component: submenu, 
        children: [                                     //嵌套
          {path: '/home/home', component: homehome}     //以 / 开头的嵌套路径会被当作根路径
        ]
      },
      {
        path:'*', redirect:'/'                          //重定向*
      }
    ]

    // 3、创建 router 实例。
    const router = new VueRouter({
      routes // (缩写) 相当于 routes: routes
    })
    
    // 4、创建和挂载根实例。
    new Vue({
      el: "#app",
      router,
      components: {App},
      template: '<App/>',
    });

#   命名路由

    routes: [
      {
        path: '/user/:userId',
        name: 'user',
        component: User
      }
    ]
    
    // 导航/user/123
    <router-link :to="{ name: 'user', params: { userId: 123 }}">User</router-link>
    router.push({ name: 'user', params: { userId: 123 }})

    
#   访问

+   通过 this.$router 访问路由器

        this.$router.go(-1);            //导航
        this.$router.push('/');
        
        router.go(n)
        // 在 history 记录中向前或者后退多少步
        // 类似 window.history.go(n)
        
        router.push(location, onComplete?, onAbort?)
        // 导航，向 history 栈添加一个新的记录，当点击浏览器后退按钮时，将回到之前的 URL。
        // 等于点击<router-link :to="...">
        router.push('/user')
        router.push({ path: '/user' })        
        router.push({ name: 'user', params: { userId }})         // -> /user/123
        router.push({ path: `/user/${userId}` })                 // -> /user/123
        router.push({ path: '/user', query: { id: '123' }})      // -> /user?id=123
        
        router.replace(location, onComplete?, onAbort?)
        // 不会向 history 添加新记录，而是替换掉当前的 history 记录。
        // <router-link :to="..." replace>

+   通过 this.$route  访问当前路由

        this.$route.params.username;    //查询参数


#   动态路径 :id
    
    routes: [
      // 动态路径参数 以冒号开头
      { path: '/user/:id', component: User }
    ]

    //查询
    $route.params.id

#   复用组件，响应路由参数的变化，watch，beforeRouteUpdate

    const User = {
      template: '...',
      watch: {
        '$route' (to, from) {
          // 对路由变化作出响应...
        }
      }
    }
    
    //或者使用 2.2 中引入的 beforeRouteUpdate 守卫：
    const User = {
      template: '...',
      beforeRouteUpdate (to, from, next) {
        // react to route changes...
        // don't forget to call next()
      }
    }


#   重定向与别名
    new VueRouter({
      routes: [
        { path: '/a', redirect: '/b' },             // 从 /a 重定向到 /b
        { path: '/c', redirect: { name: 'foo' }},   // 重定向的目标是一个命名的路由
        { path: '/d', redirect: to => { ... }},     // 动态返回重定向目标，接收目标路由('/d')作为参数，return 重定向的 字符串路径/路径对象
        { path: '/e', component: E, alias: '/f' },  // /e 的别名是 /f，当用户访问 /f 时，URL 会保持为 /f，但是路由匹配则为 /e，就像用户访问 /e ，可以自由地将 UI 结构映射到任意的 URL
     ]
    });












