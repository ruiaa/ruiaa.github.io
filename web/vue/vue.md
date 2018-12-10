
    <template>
        文本插值：<div>{{ message }}</div>
        原始HTML：<div v-html="rawHtml"></div>
        属性：<span v-bind:title="message">
        条件：<p v-if="seen">。。。</p>
        循环：<li v-for="todo in todos">{{ todo.text }}</li>
        输入：<button v-on:click="reverseMessage">
              <input v-model="useInput">
        js表达式：{{ message.split('').reverse().join('') }}
                  <div v-bind:id="'list-' + id"></div>
        指令：v- 前缀的特殊属性
              <p v-if="seen">。。。</p>
              <a v-bind:href="url"></a>
        修饰符：以 . 指明的特殊后缀
                .prevent 令 v-on 指令对于触发的事件调用 event.preventDefault()
                <form v-on:submit.prevent="onSubmit"></form>
        计算属性: <p>{{ reversedMessage2}}</p>
    </template>
    
    <script>
      import ...
      
      export default {
        data () {
          return {
            message: 'Hello Vue!',
            useInput: '',
            todos: [{ text: '学习 JavaScript' }, { text: '学习 Vue' } ]
          }
        },
        methods: {
          reverseMessage: function () {
            this.message = this.message.split('').reverse().join('')
          }
        },
        computed: {
          reversedMessage2: function () {
            return this.message.split('').reverse().join('')
          }
        },
        watch: { 
          //侦听属性
          vmDataName: function(var){...} 
          //路由变化
          '$route'(to, from) {
            ...
          }
        },
        mounted(){
          //挂载结束状态，dom渲染完成
          //可以请求数据进行填充
        },
        updated(){
          //更新完成状态
        },
        destroyed(){
          //销毁完成状态
        }
      }
    </script>   
    
    <style>
    </style>
    

#   DOM渲染：声明+数据绑定
+   文本插值：{{ data }}

        <div id="app">
          {{ message }}
        </div>
        
        var app = new Vue({
          el: '#app',
          data: {
            message: 'Hello Vue!'
          }
        })

+   绑定 DOM 元素属性  v-bind:attr="data"

        <div id="app-2">
          <span v-bind:title="message">
            鼠标悬停几秒钟查看此处动态绑定的提示信息！
          </span>
        </div>
        
        
        var app2 = new Vue({
          el: '#app-2',
          data: {
            message: '页面加载于 ' + new Date().toLocaleString()
          }
        })
        
#   条件与循环
+   v-if="data"

        <div id="app-3">
          <p v-if="seen">现在你看到我了</p>
        </div>
        
        
        var app3 = new Vue({
          el: '#app-3',
          data: {
            seen: true
          }
        })
        
+   v-for="adata in datas"

        <div id="app-4">
          <ol>
            <li v-for="todo in todos">
              {{ todo.text }}
            </li>
          </ol>
        </div>
        
        
        var app4 = new Vue({
          el: '#app-4',
          data: {
            todos: [
              { text: '学习 JavaScript' },
              { text: '学习 Vue' },
              { text: '整个牛项目' }
            ]
          }
        })
        
        
#   处理用户输入
+   v-on:event="functionVar"

        <div id="app-5">
          <p>{{ message }}</p>
          <button v-on:click="reverseMessage">逆转消息</button>
        </div>
        
        
        var app5 = new Vue({
          el: '#app-5',
          data: {
            message: 'Hello Vue.js!'
          },
          methods: {
            reverseMessage: function () {
              this.message = this.message.split('').reverse().join('')
            }
          }
        })


+   v-model="data"

        <div id="app-6">
          <p>{{ message }}</p>
          <input v-model="message">
        </div>
        
        
        var app6 = new Vue({
          el: '#app-6',
          data: {
            message: 'Hello Vue!'
          }
        })
        
#   组件化
+   Vue.component注册组件 --> html标签 

        <div id="app-7">
          <ol>
            <!--
              现在我们为每个 todo-item 提供 todo 对象
              todo 对象是变量，即其内容可以是动态的。
              我们也需要为每个组件提供一个“key”，晚些时候我们会做个解释。
            -->
            <todo-item
              v-for="item in groceryList"
              v-bind:todo="item"
              v-bind:key="item.id">
            </todo-item>
          </ol>
        </div>
        
        
        Vue.component('todo-item', {
          props: ['todo'],
          template: '<li>{{ todo.text }}</li>'
        })
        var app7 = new Vue({
          el: '#app-7',
          data: {
            groceryList: [
              { id: 0, text: '蔬菜' },
              { id: 1, text: '奶酪' },
              { id: 2, text: '随便其他什么人吃的东西' }
            ]
          }
        })
        

#   Vue 实例

    var vm = new Vue({
      // 选项
    })
    
+   数据与方法：当一个 Vue 实例被创建时，它向 Vue 的响应式系统中加入了其 data 对象中能找到的所有的属性。当这些属性的值发生改变时，视图将会产生“响应”
    +   Vue 实例暴露了一些有用的实例属性与方法。它们都有前缀 $
    
+   生命周期：created、mounted、updated、destroyed
    +   要在选项属性或回调上使用箭头函数，比如 created: () => console.log(this.a) 或 vm.$watch('a', newValue => this.myMethod())。因为箭头函数是和父级上下文绑定在一起的，this 不会是如你所预期的 Vue 实例，且 this.a 或 this.myMethod 也会是未定义的。


#   模板语法
##  插值
+   文本 {{ data }}
    
        <span v-once>这个将不会改变: {{ msg }}</span>
        //Mustache 标签将会被替代为对应数据对象上 msg 属性的值
        // v-once 指令 只执行一次性地插值
 
+   原始HTML  v-html="rawHtml"

        <div v-html="rawHtml"></div>
        //双大括号会将数据解释为普通文本，而非 HTML 代码
        //div 的内容将会被替换成为属性值 rawHtml
        
+   html属性   v-bind

        <div v-bind:id="dynamicId"></div>

+   JavaScript 表达式 （只能包含单个表达式） 在所属 Vue 实例的数据作用域下作为 JavaScript 被解析

        {{ number + 1 }}
        {{ ok ? 'YES' : 'NO' }}
        {{ message.split('').reverse().join('') }}
        <div v-bind:id="'list-' + id"></div>
        
        <!-- 这是语句，不是表达式 -->
        {{ var a = 1 }}
        <!-- 流控制也不会生效，请使用三元表达式 -->
        {{ if (ok) { return message } }}
        
+   指令 (Directives) 是带有 v- 前缀的特殊属性

        <p v-if="seen">现在你看到我了</p>
        
+   一些指令能够接收一个“参数”，在指令名称之后以冒号表示

        <a v-bind:href="url"></a>
        
+   修饰符 (Modifiers) 是以半角句号 . 指明的特殊后缀，用于指出一个指令应该以特殊方式绑定

        //.prevent 修饰符告诉 v-on 指令对于触发的事件调用 event.preventDefault()：
        <form v-on:submit.prevent="onSubmit"></form>

+   缩写

        <!-- 完整语法 -->
        <a v-bind:href="url"></a>
        <!-- 缩写 -->
        <a :href="url"></a>
        
        <!-- 完整语法 -->
        <a v-on:click="doSomething"></a>
        <!-- 缩写 -->
        <a @click="doSomething"></a>


+   
+   计算属性 computed: { propertyName : function(){ return value } }

        <div id="example">
          <p>Original message: "{{ message }}"</p>
          <p>Computed reversed message: "{{ reversedMessage }}"</p>
        </div>
        var vm = new Vue({
          el: '#example',
          data: {
            message: 'Hello'
          },
          computed: {
            reversedMessage: function () {
              return this.message.split('').reverse().join('')
            }
          }
        })
        
        
        // getter    setter
        computed: {
          fullName: {
            get: function () {
              return this.firstName + ' ' + this.lastName
            },
            set: function (newValue) {
              var names = newValue.split(' ')
              this.firstName = names[0]
              this.lastName = names[names.length - 1]
            }
          }
        }
        
+   方法 methods: { funcName : function(){ ... } }

+   被观察的属性 watch: { vmDataName: function(var){...} }

