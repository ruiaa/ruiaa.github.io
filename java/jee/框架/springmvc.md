Request（请求）
—> DispatcherServlet（核心分发器） 
—> HandlerMapping（处理器映射） 
—> Controller（控制器） 
—> ModelAndView（模型和视图） 
—> ViewResolver（视图解析器） 
—> View（视图） 
Response（响应）
--------------------- 

DispatcherServlet、HandlerMapping和ViewResolver 只需要在XML文件中配置

#   控制器Controller（即Java类）
        public class CeshiController extends AbstractController {
            @Override
            protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
                System.out.println(request.getRequestURI());  // 获取Controller的名称，即地址
                return new ModelAndView("index");  // 逻辑名
            }
        }

#   DispatcherServlet，即核心分发器，web.xml
        <web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

            <!-- 配置 DispatcherServlet，对所有后缀为action的url进行过滤 -->
            <servlet>
                <servlet-name>action</servlet-name>
                <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            </servlet>
            <servlet-mapping>
                <servlet-name>action</servlet-name>
                <url-pattern>*.action</url-pattern>
            </servlet-mapping>
        </web-app>


#   编辑 JSP 页面，用于显示

#   声明 Controller 和配置 ViewResolver，action-servlet.xml
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans       "
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">

            <!-- 声明 Controller -->
            <bean name="/home.action" class="spring.mvc.controller.CeshiController" />

            <!-- 内部资源视图解析器，前缀 + 逻辑名 + 后缀 -->
            <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
                <property name="prefix" value="/WEB-INF/pages/"/>
                <property name="suffix" value=".jsp"/>
            </bean>
        </beans>
        *


#   RequestMapping

    @Target({ElementType.METHOD, ElementType.TYPE})         //可以在方法和类的声明中使用
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Mapping
    public @interface RequestMapping {
        String name() default "";                           //字符串
        String[] value() default {};                        //字符串数组
        String[] path() default {};
        RequestMethod[] method() default {};
        String[] params() default {};
        String[] headers() default {};
        String[] consumes() default {};
        String[] produces() default {};
    }

+   value,path : 作用相同，可以互换，如果仅有这一个属性，则可以省略

      @Controller                             //Controller上不添加@RequestMapping注解
      public class UserController {
      
          @RequestMapping("/login")           //请求URL是相对于Web根目录
          public String login() {
              return "success";
          }
      }
      
      @Controller                             //Controller添加@RequestMapping注解
      @RequestMapping("/user")                //类的注解是相对于Web根目录
      public class UserController {
      
          @RequestMapping("/login")           //方法上的是相对于类上的路径
          public String login() {
              return "success";
          }
      }

+   method:定义接收浏览器发来的何种请求
      
      @RequestMapping(path = "/login", method=RequestMethod.GET)
      
      @RequestMapping(path = "/login", method={RequestMethod.POST,RequestMethod.GET})
      
+   params:限制请求参数，来实现进一步的过滤请求

      @RequestMapping(path = "/user/login", params={"username=aaa","password=123456"})
      public String login() {
          return "success";
      }
      //仅接受请求：/user/login?username=aaa&password=123456
      //其他将返回HTTP 404

+   headers:限制请求头

      @RequestMapping(path = "/user/login", headers="Host=localhost:8080")
      public String login() {
          return "success";
      }
      //表示只接收本机发来的请求

+   带占位符的URL，{id} --> @PathVariable("id")

      @RequestMapping(value="/user/{id}", method=RequestMethod.GET)
      public String show(@PathVariable("id") Integer id) {
          return "success";
      }




























