#   Java 构建

##  使用 Java 插件
    apply plugin: 'java'

默认在 src/main/java 目录下寻找源码,

在 src/test/java 目录下寻找测试源码, 

在src/main/resources目录下寻找准备打包进jar的资源文件。

测试代码会被加入到环境变量中设置的目录里运行。

所有的输出文件都会被创建在构建目录里,生成的JAR文件会被存放在 build/libs 目录下



+   Java 插件的任务：
    +   build：完全构建项目，编译和测试代码,并生成一个包含所有类与资源的 JAR 文件
    +   clean：删除 build 目录和所有为build生成的文件
    +   assemble：编译并打包代码, 但是不运行单元测试（使用 War 插件,这个任务还将根据项目生成一个 WAR 文件）
    +   check：编译并测试代码（如果使用 checkstyle 插件, 这个任务将会运行 Checkstyle 来检查代码）



##  外部的依赖
    //build.gradle
    repositories {
        mavenCentral()      //Maven 仓库
    }
    
    dependencies {
        compile group: 'commons-collections', name: 'commons-collections', version: '3.2'
        testCompile group: 'junit', name: 'junit', version: '4.+'
    }

##  定制项目
+   定制 MANIFEST.MF 文件
        
        //build.gradle
        sourceCompatibility = 1.5
        version = '1.0'
        jar {
            manifest {
                attributes 'Implementation-Title': 'Gradle Quickstart', 'Implementation-Version': version
            }
        }

+   系统属性
        
        //测试阶段加入一个系统属性
        test {
            systemProperties 'property': 'value'
        }

##  发布 JAR 文件
    //build.gradle
    uploadArchives {
        repositories {
            flatDir {
                dirs 'repos'
            }
        }
    }


##  Eclipse 项目

    apply plugin: 'java'
    apply plugin: 'eclipse'
    
    sourceCompatibility = 1.5
    version = '1.0'
    jar {
        manifest {
            attributes 'Implementation-Title': 'Gradle Quickstart', 'Implementation-Version': version
        }
    }
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        compile group: 'commons-collections', name: 'commons-collections', version: '3.2'
        testCompile group: 'junit', name: 'junit', version: '4.+'
    }
    
    test {
        systemProperties 'property': 'value'
    }
    
    uploadArchives {
        repositories {
            flatDir {
                dirs 'repos'
            }
        }
    }



##  多项目
+   分层目录

        multiproject/
            api/
            services/webservice/
            shared/

+   定义多项目构建：settings.gradle
        
        //settings.gradle   源代码的根目录
        include "shared", "api", "services:webservice", "services:shared"

+   通用配置

    配置注入技术(configuration injection):根项目作为容器, subprojects方法遍历这个容器的所有元素并且注入指定的配置
    
        //build.gradle   源代码的根目录
        subprojects {
            apply plugin: 'java'
            apply plugin: 'eclipse-wtp'
            
            repositories {
                mavenCentral()
            }
            
            dependencies {
            testCompile 'junit:junit:4.11'
            }
            
            version = '1.0'
            
            jar {
                manifest.attributes provider: 'gradle'
            }
        }

+   项目之间的依赖
        
        //api/build.gradle
        dependencies {
            compile project(':shared')      //由于这个依赖, Gradle 将确保 shared 项目总是在 api 之前被构建
        }
        
+   创建一个发行版本
        
        //api/build.gradle
        task dist(type: Zip) {
            dependsOn spiJar
            from 'src/dist'
            into('libs') {
                from spiJar.archivePath
                from configurations.runtime
            }
        }
        
        artifacts {
            archives dist
        }

















