#	功能
+	全功能栈（full-stack）的应用程序框架
+	控制反转(IOC)：依赖被注入到对象，而不是创建或寻找依赖对象
+	面向切面编程(AOP)：把应用的业务逻辑与系统的服务分离开来
+	MVC框架
+	事务管理
+	单元测试

#	IoC容器	Bean
+	配置形式
	+	基于XML文件
  		
      id：Bean 的名称。 在 IOC 容器中必须是唯一的。若 id 没有指定，Spring 自动将权限定性类名作为 Bean 的名字。id 可以指定多个名字，名字之间可用逗号、分号、或空格分隔
  		
      <bean id="helloWorld" class="com.spring.helloworld.HelloWorld">
        <!-- 为属性赋值 -->
        <property name="user" value="Jerry"></property>
    	</bean>
      
	+	基于注解
		+	组件扫描（component scanning）：Spring能够从classpath下自动扫描，侦测和实例化具有特定注解的组件。 
      
      @Component:基于注解，标识了一个受Spring管理的组件；
      @Resposity:标识持久层组件；
      @Service:标识服务层（业务层）组件；
      @Controller:标识表现层组件；

      
      @Autowired默认按类型匹配注入Bean;
      @Resource按名称匹配注入Bean;
      @Inject和@Autowired一样按类型注入Bean的，只不过他没有required属性。

      
  	+	Spring通过@Autowired注解实现Bean的依赖注入 
  		
      //1.首先通过注解的方式创建bean

      @Component 
      @Scope("prototype") //多态的Bean
      public class Student {
      
      }

      
      @Scope(“prototype”)   
      @Lazy(true)   
      @Component(“userDao”)   
      public class UserDao {   
          ……   
          // 用于设置初始化方法   
          @PostConstruct  
          public void myInit() {   
      
          }   
      
          // 用于设置销毁方法   
          @PreDestroy  
          public void myDestroy() {   
          }   
      }
      
      //2.通过@Autowire注入
      
      public class Use {
      
          @Autowired
          @Qualifier("car")
          private Car car;
      
          @Resource
          private  UserDao userDao; 
          ....
          public String toString(){
              return car.toString();
          }
      
      }

	+	基于java配置


+	获取Bean实例
	+	IOC 容器实现:BeanFactory，ApplicationContext
	
  +	BeanFactory，通过工厂方法（静态工厂方法&实例工厂方法）
  
  		public static void main(String[] args) {  
          //实例化BeanFactory  
          BeanFactory factory = new BeanFactory();  
          //调用初始化方法，传入xml路径  
          factory.init("spring.xml");  
          //通过bean id 获取对象  
          Car car = (CourseService) factory.getBean("car");  
          System.out.print(car);   
      }  

	+	ApplicationContext
  		
      //从 IOC 容器中获取 Bean 调用 ApplicationContext 的 getBean() 方法
      //ClassPathXmlApplicationContext:从类路径下加载配置文件
			//FileSystemXmlApplicationContext:从文件系统中加载配置文件

+	依赖注入的方式
	+	属性注入:setter方法
  		<bean id="helloWorld2" class="com.spring.helloworld.HelloWorld">
        <!-- 为属性赋值 -->
        <!-- 通过属性注入: 通过 setter 方法注入属性值 -->
        <property name="user" value="Tom"></property>
    	</bean>
      
	+	构造器注入
  		//按索引匹配入参
      <bean id="car" class="com.spring.helloworld.Car">
        <constructor-arg value="KUGA" index="0"></constructor-arg>
        <constructor-arg value="ChangAnFord" index="1"></constructor-arg>
        <constructor-arg value="250000" index="2"></constructor-arg>
      </bean>
      
      //按类型匹配入参
      <bean id="car" class="com.spring.helloworld.Car">
        <constructor-arg value="KUGA" type="java.util.String"></constructor-arg>
        <constructor-arg value="ChangAnFord" type="string"></constructor-arg>
        <constructor-arg value="250000" type="float"></constructor-arg>
      </bean>

+	引用其他的Bean
	+	通过 <ref>元素或 ref 属性为 Bean 的属性或构造器参数指定对 Bean 的引用.

      <bean id="dao5" class="com.spring.ref.Dao"></bean>
      
      <bean id="service" class="com.spring.ref.Service">
          <!-- 通过 ref 属性值指定当前属性指向哪一个 bean! -->
          <property name="dao" ref="dao5"></property>
      </bean>
      <bean id="action" class="com.spring.ref.Action">
          <property name="service" ref="service2"></property>
          <!-- 设置级联属性(了解) -->
          <property name="service.dao.dataSource" value="DBCP2"></property>
      </bean>

+	内部 Bean
	+	Bean 实例仅仅给一个特定的属性使用,不需要设置任何 id 或 name 属性
  
      <bean id="service2" class="com.spring.ref.Service">
          <property name="dao">
              <!-- 内部 bean, 类似于匿名内部类对象. 不能被外部的 bean 来引用, 也没有必要设置 id 属性 -->
              <bean class="com.spring.ref.Dao">
                  <property name="dataSource" value="c3p0"></property>
              </bean>
          </property>
      </bean>


+	null 值
	+	可以使用专用的 <null/> 元素标签为 Bean 的字符串或其它对象类型的属性注入 null 值
  
      <bean id="dao2" class="com.spring.ref.Dao">
        <!-- 为 Dao 的 dataSource 属性赋值为 null, 若某一个 bean 的属性值不是 null, 使用时需要为其设置为 null(了解) -->
        <property name="dataSource"><null/></property>
      </bean>

+	集合属性
	+	通过一组内置的 xml 标签(例如: <list>, <set> 或 <map>) 来配置集合属性

    <bean id="user" class="com.spring.helloworld.User">
        <property name="userName" value="Jack"></property>
        <property name="cars">
            <!-- 使用 list 元素来装配集合属性 -->
            <list>
                <ref bean="car"/>
                <ref bean="car2"/>
            </list>
        </property>
    </bean>

    <!-- 声明集合类型的 bean -->
    <util:list id="cars">
        <ref bean="car"/>
        <ref bean="car2"/>
    </util:list>

    <bean id="user2" class="com.spring.helloworld.User">
        <property name="userName" value="Rose"></property>
        <!-- 引用外部声明的 list -->
        <property name="cars" ref="cars"></property>
    </bean>

+	p 命名空间

    <bean id="user3" class="com.spring.helloworld.User"
          p:cars-ref="cars" p:userName="Titannic">
    </bean>

+	bean 之间的关系
	+	继承：parent
    <bean id="user" class="com.spring.helloworld.User">
        <property name="userName" value="Jack"></property>
        <property name="cars">
            <!-- 使用 list 元素来装配集合属性 -->
            <list>
                <ref bean="car"/>
                <ref bean="car2"/>
            </list>
        </property>
    </bean>
    
    <!-- bean 之间使用 parent 来完成继承 --> 
    <bean id="user4" parent="user" p:userName="Bob"></bean>
    
    <bean id="user6" parent="user" p:userName="维多利亚"></bean>

    
  +	依赖：depends-on，前置依赖的Bean会在本Bean实例化之前创建好
    <bean id="user5" parent="user" p:userName="Backham" depends-on="user6"></bean>

    
	+	关联：ref
    <bean id="userDao" class="com.spring.ref.UserDao">
    </bean>

    <bean id="userService" class="com.spring.ref.UserService">
        <property name="userDao" ref="userDao">
        </property>  
    </bean>

    <bean id="userAction" class="com.spring.ref.UserAction">
        <property name="userService" ref="userService">
        </property>
    </bean>

+	bean 的作用域：singleton、prototype
    <bean id="helloWorld" 
        class="com.spring.helloworld.HelloWorld" 
        scope="singleton"
        init-method="init"
        destroy-method="destroy">
        <property name="userName" value="atguigu"></property>
    </bean>
    
    singleton	在SpringIOC容器中仅存在一个Bean实例，Bean以单例的方式存在
    prototype	每次调用getBean()时都会返回一个新的实例
    request	每次HTTP请求都会创建一个新的Bean，该作用域仅适用于WebApplicationContext环境
    session	同一个HTTP session共享一个Bean，不同的HTTP session使用不同的Bean，该作用域仅适用于WebApplicationContext环境



















