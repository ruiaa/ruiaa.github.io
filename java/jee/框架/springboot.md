#	使用idea创建springboot项目
+	New > Project > Spring Initializr > Web

#	项目结构
+	src
	+	main
  	+	java/com/ruiaa/BootdemoApplication.java		：一个带有main()方法的类，用于启动应用程序（关键）
  +	resources
  	+	static
    +	templates
    +	application.properties		：一个空的properties文件，可添加配置属性
  +	pom.xml											：Maven构建说明文件

#	pom.xml

#	启动项目
+	Application.java的main方法
+	命令启动 mvn spring-boot:run
+	jar打包命令 mvn package

#	静态资源处理
src/main/resources/META-INF/resources
src/main/resources/resources
src/main/resources/static
src/main/resources/public

#	Starter
一组依赖项（比如 Maven POM）
命名约定：spring-boot-starter-XYZ
+	spring-boot-starter-web 用于构建 RESTful Web 服务，它使用 Spring MVC 和 Tomcat 作为嵌入式应用程序容器
+	spring-boot-starter-jersey 是 spring-boot-starter-web 的一个替代，它使用 Apache Jersey 而不是 Spring MVC
+	spring-boot-starter-jdbc 用于建立 JDBC 连接池。它基于 Tomcat 的 JDBC 连接池实现。



#	嵌入式 Tomcat 容器

#	注解
+	启动器：@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan

+	控制器：
	+	类：@RestController = @Controller + @ResponseBody
  	+	@ResponseBody:该注解指示方法返回值的应绑定到Web响应正文
    +	@RestController：暗示用户，这是一个支持REST的控制器
  +	@RequestMapping:是一个用来处理请求地址映射的注解，可用于类或方法上。
  	+	用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
    +	根据方法的不同，还可以用GetMapping、PostMapping、PutMapping、DeleteMapping、PatchMapping代替。

#	自定义属性：application.properties
+	com.ruiaa.propertyName=valve
+	通过@Value("${属性名}")注解来加载对应的配置属性

		@Value("${com.neo.title}")
		private String title;

#	Beans和依赖注入
+	使用 @ComponentScan 注解搜索beans，并结合 @Autowired 构造器注入
+	将应用的main类放到包的最上层，即root package，所有应用组件（ @Component , @Service , @Repository , @Controller 等）都会自动注册成Spring Beans。

    @Service
    public class DatabaseAccountService implements AccountService {
      private final RiskAssessor riskAssessor;
      
      //使用构建器注入获取一个需要的RiskAssessor bean
      @Autowired
      public DatabaseAccountService(RiskAssessor riskAssessor) {
        this.riskAssessor = riskAssessor;
      }
    
    }
    
    @Service用于标注业务层组件
    @Controller用于标注控制层组件
    @Repository用于标注数据访问组件，即DAO组件
    @Component泛指组件，当组件不好归类的时候，可使用这个注解进行标注。


#	MVC
+	@RequestMapping(value = "/hello", method = RequestMethod.GET)
+	@ResponseBody 返回结果直接写入Http Response Body。 具体格式由HttpMessageConverter决定，SpringMVC中默认为json，自动将 java bean 对象转成 json 串
+	@RequestParam("paramName") 直接自动装载参数,如果HTTP请求中没有该参数时Spring MVC就会报错
+	@RequestParam(value = "paramName", defaultValue = "")
+	@PathVariable 来装载URL路径中的值作为参数
+	@RestController = @Controller + @ResponseBody，有@RestController的类相当于每个方法（有@RequestMapping的）自动加上了 @ResponseBody 注解
+	 response.sendRedirect("some-url"); 重定向

		@Controller
		public class HelloController {

    		@Autowired
    		private HelloService helloService;
    
    		@RequestMapping(value = "/hello", method = RequestMethod.GET)
    		public void sayHello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        		String name = request.getParameter("name");
        		String msg = helloService.hello(name);
        		response.getWriter().println(msg);
    		}
        
        @RequestMapping(value = "/hello2/param", method = RequestMethod.GET)
        @ResponseBody
        public String sayHelloByParam(@RequestParam("name") String name) {
            return helloService.hello(name);
        }
    
        // URL示例：  http://localhost:8080/hello2/path/jjj
        @RequestMapping(value = "/hello2/path/{name}", method = RequestMethod.GET)
        @ResponseBody
        public String sayHelloByPath(@PathVariable("name") String name) {
            return helloService.hello(name);
        }

		}

{
    "code": 200,
    "msg": "success",
    "exception": null,
    "data": "ppateeeeeeeeeeehbehrqwrfwqcvwqcp"
}
+	自定义返回值转化类 HttpMessageConverter
    
@EnableWebMvc
public class HelloWebMvcConfigurer extends WebMvcConfigurerAdapter {

    public static final ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        // 属性为空时（包括 null, 空串，空集合，空对象），不参与序列化。
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // Date 对象在序列化时，格式为 yyyy年MM月dd日 HH时mm分ss秒 。
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // json串以良好的格式输出。
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // 当属性为空或有问题时不参与序列化。
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 未知的属性不参与反序列化。
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);       
        return objectMapper;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = createObjectMapper();
        MappingJackson2HttpMessageConverter convertor = new MappingJackson2HttpMessageConverter(objectMapper);
        //Spring MVC 中可能有很多 HttpMessageConverter 对象，运行时是从 converters 列表中遍历，直到找到了一个 HttpMessageConverter 对象能处理当前的HTTP请求为止。
        //0为第一位，最高优先权
        converters.add(0, convertor);
    }
}

//用 @Import 注解在主程序中引入
@Import(HelloWebMvcConfigurer.class)
@SpringBootApplication
public class HelloWebApp {

    public static void main(String[] args) {
        SpringApplication.run(HelloWebApp.class, args);
    }


}

#	MVC配置
+	Spring Boot通过WebMvcAutoConfiguration来提供一些默认配置
+	使用EnableWebMvc注解：同一个应用中，只能有一个被EnableWebMvc注解的类

    @Configuration
    @EnableWebMvc
    public class MyWebMvcConfigurer implements WebMvcConfigurer {
    
    }
    
    configurePathMatch	配置HandlerMapping路径匹配参数
    configureContentNegotiation	配置路径到请求内容类型转换的相关参数，如.pdf结尾的请求解析成PDF类型或者其它等
    configureAsyncSupport	配置异步请求处理相关参数
    configureDefaultServletHandling	配置是否需要以下功能：如果一个请求没有被任何Handler处理，那是否使用DefaultServletHttpRequestHandler来进行处理？
    addFormatters	增加额外的Converter和Formatter
    addInterceptors	增加拦截器
    addResourceHandlers	增加处理静态资源的Handler
    addCorsMappings	配置跨域请求相关参数
    addViewControllers	使用特殊的Controller来处理指定的URL请求;
    configureViewResolvers	配置将Controller返回的视图名称转换成视图的视图解析器; 以便进行视图渲染
    addArgumentResolvers	添加支持个性化配置Controller的方法参数类型的Resolver。
    addReturnValueHandlers	添加支持个性化处理Controller返回数据类型的处理器；
    configureMessageConverters	配置消息转换器；
    extendMessageConverters	扩展消息转换器
    configureHandlerExceptionResolvers	配置异常处理器
    extendHandlerExceptionResolvers	扩展异常处理器


#	Hibernate  ：  JPA：Java Persistence API，Java持久层API  <-- Spring Data JPA	
[Spring Data JPA](https://www.jianshu.com/p/be542258802e)
+	1.在pom.xml中配置spring-boot-starter-data-jpa，及在 application配置文件中配置数据库连接。

    <!-- JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
		
    //在application.properties配置文件中配置数据库连接及JPA配置
    spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
    spring.datasource.url = jdbc:mysql://127.0.0.1:3306/db?serverTimezone=UTC&characterEncoding=utf-8
    spring.datasource.username = root
    spring.datasource.password = 

    //每次程序启动时对数据库表的处理策略，create：删除上次后创建，create-drop：启动时创建退出时删除，update：第一次访问时创建以后更新，validate：不创建新表仅插入新值
    spring.jpa.hibernate.ddl-auto = update
    spring.jpa.show-sql = true

+	2.编写 Entity 类，依照 JPA 规范，定义实体。


@Entity(name = "t_user")		// 定义数据库表名称。name:表名
@Table(indexes = { 					// 定义数据库索引。name:表中索引的名称， columnList:属性名称(多个属性表示联合索引）， unique=true:唯一索引
	@Index(name = "ux_user_login_name", columnList = "loginName", unique = true), 
	@Index(name = "idx_user_age", columnList = "age,loginName"), 
})
public class User {

		//在主建Id上需要加注释：@Id和@GeneratedValue(strategy = GenerationType.AUTO)才会自动增长
    //在需要重新设置表字段名的属性上加注释@Column(name = "字段名")
    
    //id，自增主键
    @Id
    @GeneratedValue
    @Column(length = 20)
    private Long id;

    //属性名驼峰命名法
    //表字段下划线命名法（如loginName -> login_name）
    @Column(length = 100, nullable = false)
    private String loginName;

    @Column(length = 3)
    private Integer age;

    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.enable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date registTime;

    public final Long getId() { return id; }

    public final void setId(Long id) { this.id = id; }
}

+	3.编写 Repository 接口，依靠 Spring Data 规范，定义数据访问接口（注意，只要接口，不需要任何实现）

//实体类是 User, User 中 ID 属性是 Long 类型的
//像使用普通的 Spring Bean 一样，用 @Autowired 引入即可使用
public interface UserRepository extends JpaRepository<User, Long> {

}

+	4.使用原生SQL查询

		//SQL : src/main/java/META-INF/orm.xml 
		<?xml version="1.0" encoding="UTF-8" ?>
		<entity-mappings xmlns="http://xmlns.jcp.org/xml/ns/persistence/orm"    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence/orm http://xmlns.jcp.org/xml/ns/persistence/orm_2_0.xsd"  version="2.1">
    		<named-native-query 									//表示是一个“原生SQL查询”
    				name="User.findByAgeRange" 				//根据名字来引用这个查询
        		result-class="com.example.User"		//结果集的每条记录转化成User对象
      		>
      		//原生的SQL语句，以:开始的是变量，将从外面传入进来
      		<query><![CDATA[
          		select * from t_user as u
          		where u.age >= :minAge and u.age <= :maxAge
          		order by u.regist_time desc
      		]]></query>
    		</named-native-query>
		</entity-mappings>

		//定义组件
		public interface UserRepository extends JpaRepository<User, Long> {
    		@Query(nativeQuery = true, name = "User.findByAgeRange")
    		List<User> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);
		}

		//使用
		userRepository.findByAgeRange(minAge, maxAge);

#	Mybatis
+	依赖

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.1</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

+	配置文件application.yml(application.properties)

		spring:
	  datasource:
	     url: jdbc:mysql://127.0.0.1:3306/mytest?useUnicode=true&characterEncoding=utf-8
	     username: root
	     password: root
	     driver-class-name: com.mysql.jdbc.Driver
     
+	在Mysql数据库中创建数据表

+	创建实体类

		public class User {
    		private Integer id;
    		private String name;
		    //省略 get 和 set ...
		}

+	创建映射的操作，@Autowired注入使用

		@Mapper
		public interface UserMapper {
		    @Select("SELECT * FROM T_USER WHERE PHONE = #{phone}")
		    User findUserByPhone(@Param("phone") String phone);

		    @Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
		    int insert(@Param("name") String name, @Param("password") String password, @Param("phone") String phone);
		}

    //使用
    userMapper.insert("winterchen", "123456", "12345678910");
    User u = userMapper.findUserByPhone("12345678910");
    
    //增删改查
    @Select("SELECT * FROM T_USER WHERE PHONE = #{phone}")
    User findUserByPhone(@Param("phone") String phone);
    @Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
    int insertByUser(User user);
    @Update("UPDATE T_USER SET NAME = #{name}, PASSWORD = #{password} WHERE PHONE = #{phone}")
    void update(User user);
    @Delete("DELETE FROM T_USER WHERE ID = #{id}")
    void delete(Integer id);
    
    //使用Map
    @Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(" +
            "#{name, jdbcType=VARCHAR}, #{password, jdbcType=VARCHAR}, #{phone, jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);

    Map<String, Object> map = new HashMap<>();
    map.put("name","王五");
    map.put("password","23423");
    map.put("phone", "13400000000");
    userMapper.insertByMap(map);

    //使用对象，#{name}、#{age}就分别对应了User对象中的name和age属性
    @Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
    int insertByUser(User user);
    
    

+	事务管理；在需要事务管理的方法上添加 @Transactional 注解
	+	在Application使用注解 @EnableTransactionManagement 开启事务支持	
	+	当使用@Transactional进行了事务管理，在单元测试结束之后会自动回滚

+	返回结果绑定：@Results、@Result

    @Results({
            @Result(property = "name", column = "NAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "phone", column = "PHONE")
    })
    //部分查询
    @Select("SELECT NAME, PASSWORD, PHONE FROM T_USER")
    List<User> findAll();

+	分页插件PageHelper

#	filters
+	OrderedCharacterEncodingFilter和HiddenHttpMethodFilter

@Configuration
public class WebConfiguration {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }
    
    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
    
    public class MyFilter implements Filter {
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
		}

		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
				throws IOException, ServletException {
			// TODO Auto-generated method stub
			HttpServletRequest request = (HttpServletRequest) srequest;
			System.out.println("this is MyFilter,url :"+request.getRequestURI());
			filterChain.doFilter(srequest, sresponse);
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
		}
    }
}


#	@Configuration






#	测试
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisDemo2ApplicationTests {


    @Autowired
    private UserMapper userMapper;


    @Test
    @Transactional
    public void test3(){
        userMapper.insert("张三", "123456", "18600000000");

        User u = userMapper.findUserByPhone("18600000000");

        Assert.assertEquals("123456", u.getPassword());

        u.setName("赵六");
        u.setPassword("12312312");
        userMapper.update(u);

        u = userMapper.findUserByPhone("18600000000");

        Assert.assertEquals("12312312", u.getPassword());

        userMapper.delete(u.getId());

        u = userMapper.findUserByPhone("18600000000");

        Assert.assertEquals(null, u);
    }
}




















































