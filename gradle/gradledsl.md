#   组成：project + tasks
+   项目是指构建产物（比如Jar包）或实施产物（将应用程序部署到生产环境）。
+   任务是指不可分的最小工作单元，执行构建工作（比如编译项目或执行测试）。
+   
+   Gradle构建系统：
    +   build.gradle 构建脚本
        
        定义了一个模块和编译用的tasks，它一般是放在项目的模块中，也可以放在项目的根目录用来作为编译结构全局设置，它是必须的

    +   settings.gradle 
    
        描述了哪一个模块需要参与构建。每一个多模块的构建都必须在项目结构的根目录中加入这个设置文件，它也是必须的

    +   gradle.properties 用来配置构建属性，这个不是必须的

+   gradle对build.gradle脚本的处理

+   构建的生命周期：配置阶段,执行阶段
    
        task distribution << {
        println "We build the zip with version=$version"
        }
        task release(dependsOn: 'distribution') << {
            println 'We release now'
        }
        gradle.taskGraph.whenReady {taskGraph ->
            if (taskGraph.hasTask(release)) {
                version = '1.0'
            } else {
                version = '1.0-SNAPSHOT'
            }
        }

        > gradle -q distribution
        We build the zip with version=1.0-SNAPSHOT
        Output of gradle -q release
        > gradle -q release
        We build the zip

#   task
+   定义
    
        task taskName { 
         
         }
        
+   动态添加操作：
        
        taskName.doFirst{ 
        }
        taskName.doLast{ 
        }
        
+   依赖：
        
        task taskName(dependsOn:overTask) { 
        
        } 
         
        taskName.dependsOn overTask

+   默认任务
    
        defaultTasks 'clean', 'run'
        task clean << {
            println 'Default Cleaning!'
        }
        task run << {
            println 'Default Running!'
        }
        task other << {
            println "I'm not a default task!"
        }
        
        > gradle -q
        Default Cleaning!
        Default Running!

#   属性
+   自定义task属性
    
        task myTask {
        ext.myProperty = "myValue"
        }
        task printTaskProperties << {
        println myTask.myProperty
        }

#   插件
+   引入：build.gradle文件中加入 apply plugin: 'java'
+   常用task：
        
        gradle build：编译整个项目，它会执行代码编译、代码检测和单元测试等
        gradle assemble：编译并打包你的代码, 但是并不运行代码检测和单元测试
        gradle clean：删除 build 生成的目录和所有生成的文件
        gradle check：编译并测试你的代码。其它的插件会加入更多的检查步骤，如使用 checkstyle、pmd、findbugs


#   依赖
+   仓库地址
    
        repositories {
                mavenCentral()      //Gradle内置的一个maven仓库地址
                maven {
                    url "http://repo.mycompany.com/maven2"
                }
                ivy {
                    url "http://repo.mycompany.com/repo"
                }
        }

+   引用外部依赖
        
        dependencies {
        	compile group: 'commons-collections', name: 'commons-collections', version: '3.2'
        	// 简化写法
        	// compile 'commons-collections:commons-collections:3.2'
        }

+   本地依赖
        
        dependencies {
        	compile files('dir/file.jar')                       //单一引入指定的某一JAR包
        	compile fileTree(dir: 'libs', include: '*.jar')     //引入某目录下所有的JAR包
        }

+   项目依赖

        //使用文件settings.gradle定义当前项目的子项目
        //默认情况下，每个子项目的名称对应着当前操作系统目录下的一个子目录。
        include 'sub-project1', 'sub-project2', 'sub-project3'
        
        //子项目依赖：如sub-project1依赖sub-project2，则在sub-project1的build.gradle中配置
        dependencies {
        	compile project(':sub-project2')
        }

+   依赖关系管理

        dependencies {
            compile group: 'org.hibernate', name: 'hibernate-core', version: '3.6.7.Final'
            testCompile group: 'junit', name: 'junit', version: '4.+'
        }
        
        compile 
        	用来编译项目源代码的依赖
        runtime
        	在运行时被生成的类使用的依赖。 默认的, 也包含了compile时的依赖。
        testCompile
        	编译测试代码的依赖。 默认的, 包含runtime时的依赖和compile时的依赖。
        testRuntime
        	运行测试所需要的依赖。 默认的, 包含上面三个依赖。

#   发布
+   发布一个 Ivy 库
    
        uploadArchives {
            repositories {
                ivy {
                    credentials {
                        username "username"
                        password "pw"
                    }
                    
                    url "http://repo.mycompany.com"
                }
            }
        }

+   发布 Maven 库
    
        apply plugin: 'maven'
        
        uploadArchives {
            repositories {
                mavenDeployer {
                     repository(url: "file://localhost/tmp/myRepo/")
                }
            }
        }


#   Gradle Wrapper
+   gradlew task 

    每一个Wrapper都会绑定到一个特定版本的Gradle
    
    当用户第一次执行上面的命令时，Wrapper会自动地下载并安装对应版本的Gradle
    
    下载的Gradle发行版被存放在目录 $USER_HOME/.gradle/wrapper/dists 中 

+   Wrapper 配置
    
        gradlew (Unix) 或者 gradlew.bat (Windows)
        gradle/wrapper/gradle-wrapper.jar (Wrapper JAR)
        gradle/wrapper/gradler-wrapper.properties (Wrapper properties)

+   将Wrapper添加到Gradle项目

        //命令行执行
        $gradle wrapper --gradle-version 2.10

        //或者build.gradle文件中添加一个 Wrapper task，命令行执行 gradle -q wrapper
        task wrapper(type : Wrapper) {
          gradleVersion = '2.10'
        }

















#   gradle 命令
+   多任务调用：

    gradle compile test 
    
    命令会依次调用 compile 和 test 任务，它们所依赖的任务也会被调用. 但所有的任务只会被调用一次

+   排除任务

    gradle dist -x test 

    test任务不会被调用,即使它是dist任务的依赖，同时test任务的依赖任务也不会被调用

+   失败后继续执行构建
    
    默认情况下, 只要有任务调用失败, Gradle就会中断执行.
    
    采用 --continue 选项, Gralde 会调用每一个任务以及它们依赖的任务

+   选择执行构建
    
    调用 gradle 命令时, 默认情况下总是会构建当前目录下的文件
    
    可以使用 -b 参数选择其他目录的构建文件, 并且 settings.gradle 将不会生效

+   gradle projects 列出子项目名称列表
+   gradle tasks    列出项目中所有任务
+   gradle help --task someTask 获取任务具体信息
+   gradle dependencies     获取依赖列表
+   gradle dependencyInsight    查看指定的依赖
+   gradle properties   获取项目属性列表










