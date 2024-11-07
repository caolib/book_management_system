[![Typing SVG](https://readme-typing-svg.herokuapp.com?font=cascadia+code&size=38&duration=3500&pause=1000&color=00ADFF&center=true&vCenter=true&random=false&width=1000&height=100&lines=Book+lending+management+system;图书借阅管理系统)](https://git.io/typing-svg)

&emsp;&emsp;

![springboot](https://img.shields.io/badge/springboot-v3.0.9-%236DB33F?style=flat&logo=springboot&logoColor=236DB33F&labelColor=white)
![maven](https://img.shields.io/badge/Maven-v3.9.5-blue?style=flat&logo=apachemaven&logoColor=red&labelColor=white)
![mybatisplus](https://img.shields.io/badge/MybatisPlus-v3.5.3.1-red?style=flat&labelColor=white)
![mysql](https://img.shields.io/badge/MySQL-v8.2.0-blue?style=flat&logo=mysql&logoColor=blue&labelColor=white)
![redis](https://img.shields.io/badge/Redis-v7.0.12-red?style=flat&logo=redis&logoColor=%23DC382D&labelColor=white)
![GitHub Release](https://img.shields.io/github/v/release/tankingcao/java_design?include_prereleases&sort=date&display_name=release&style=flat&labelColor=red&cacheSeconds=3600)
![下载量](https://img.shields.io/github/downloads/caolib/book_management_system/total.svg)

<!-- 
![GitHub License](https://img.shields.io/github/license/caolib/book_management_system?style=flat)
![opened issues](https://img.shields.io/github/issues/caolib/book_management_system?color=red&cacheSeconds=3600)
![closed issues](https://img.shields.io/github/issues-closed/caolib/book_management_system?color=green&cacheSeconds=3600)
![GitHub commit activity](https://img.shields.io/github/commit-activity/y/caolib/book_management_system?labelColor=red)
-->

使用`springboot+mybatis-plus`框架制作的一个简单的图书借阅管理系统后台服务器

> [!important]
>
> **项目采用前后端分离开发，这是后端项目，对应的[前端项目地址](https://github.com/caolib/vue3-vite)，注意相关技术栈版本不要相差太大**

> [!caution]
>
> - **最近更新中因为使用`redis`二次校验token实现token主动过期，`redis`变成必需项!!!**
> - **在[发行版](https://github.com/caolib/book_management_system/releases)的资源中有此项目对应的数据库结构的`sql`文件**

> [!tip]
> - 使用前先使用maven下载相关依赖，建议使用IDEA编译器，捆绑了maven，可以直接使用
> - 注意前后端一般是同时修改的，必须匹配版本，没有特别需求（不想使用redis）直接使用最新的

## 快速开始

### 1.参照注释修改配置文件

路径：`src/main/resources/application.yml`

```yml
# 项目启动端口，默认8080，修改后前端中的请求地址也要对应修改
server:
  port: 8080

# mybatis-plus配置
mybatis-plus:
  configuration:
    map-underscore-to-camel-case: true # 下划线命名转驼峰
  type-aliases-package: com.clb.domain # 别名扫描包
  mapper-locations: classpath:mapper/*.xml # mapper文件扫描

spring:
  # mysql
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/java_design?serverTimezone=Asia/Shanghai
    # 注意修改用户名和密码为你自己的
    username: root
    password: 123456
    type: com.alibaba.druid.pool.DruidDataSource

  # redis
  data:
    redis:
      # 修改host和密码为你的，如果没有密码则删除password项，redis默认没有密码
      host: localhost
      password: 123456
      port: 6379
      database: 0
      timeout: 5000ms
  cache:
    type: redis  
    redis:
      time-to-live: 3600000 # 缓存过期时间,单位ms(此处一小时)

  # 热重载排除advice文件
  devtools:
    restart:
      additional-exclude: com/clb/util/Advice.class
  # 支持控制台ansi颜色输出(使用java命令行部署时),如果乱码则删除下面3行
  output:
    ansi:
      enabled: always
      
# 日志
logging:
  level:
    com.clb: debug
  pattern:
    dateformat: MM-dd HH:mm:ss.SSS

```

### 2.启动项目

使用编译器一键启动项目(前提:mysql和redis数据库配置正确且已经启动)

## 项目目录结构

📂 .                                                         0 B
├── 📄 LICENSE                                             1.1 KB
├── 📄 pom.xml                                             4.5 KB
├── 📄 README.md                                           5.3 KB
└── 📂 src                                                   0 B
    ├── 📂 main                                              0 B
    │   ├── 📂 java                                          0 B
    │   │   └── 📂 com                                       0 B
    │   │       └── 📂 clb                                 4.0 KB
    │   │           ├── 📂 annotation                        0 B
    │   │           │   └── 📄 MyController.java           503 B
    │   │           ├── 📄 BookApplication.java            503 B
    │   │           ├── 📂 config                            0 B
    │   │           │   ├── 📄 MybatisConfig.java          784 B
    │   │           │   ├── 📄 RedisConfig.java            1.0 KB
    │   │           │   └── 📄 WebMvcConfig.java           1.5 KB
    │   │           ├── 📂 constant                          0 B
    │   │           │   ├── 📄 Cache.java                  251 B
    │   │           │   ├── 📄 Code.java                   209 B
    │   │           │   ├── 📄 Common.java                 235 B
    │   │           │   ├── 📄 Excep.java                  1.6 KB
    │   │           │   └── 📄 Jwt.java                    207 B
    │   │           ├── 📂 controller                      4.0 KB
    │   │           │   ├── 📄 AdminController.java        908 B
    │   │           │   ├── 📄 AdminLoginController.java   1.4 KB
    │   │           │   ├── 📄 BookController.java         2.4 KB
    │   │           │   ├── 📄 BorrowController.java       2.3 KB
    │   │           │   ├── 📄 CommonController.java       804 B
    │   │           │   ├── 📄 LoginController.java        1.4 KB
    │   │           │   └── 📄 ReaderController.java       1.6 KB
    │   │           ├── 📂 domain                            0 B
    │   │           │   ├── 📄 Borrow.java                 654 B
    │   │           │   ├── 📂 dto                           0 B
    │   │           │   │   ├── 📄 Condition.java          357 B
    │   │           │   │   └── 📄 LoginDto.java           371 B
    │   │           │   ├── 📂 entity                        0 B
    │   │           │   │   ├── 📄 Admin.java              626 B
    │   │           │   │   ├── 📄 Book.java               550 B
    │   │           │   │   └── 📄 Reader.java             671 B
    │   │           │   ├── 📄 PageResult.java             896 B
    │   │           │   ├── 📄 Result.java                 997 B
    │   │           │   └── 📂 vo                            0 B
    │   │           │       ├── 📄 AdminVo.java            352 B
    │   │           │       ├── 📄 BorrowVo.java           634 B
    │   │           │       └── 📄 ReaderVo.java           411 B
    │   │           ├── 📂 exception                         0 B
    │   │           │   ├── 📄 AlreadyExistException.java  176 B
    │   │           │   └── 📄 BaseException.java          161 B
    │   │           ├── 📂 handle                            0 B
    │   │           │   └── 📄 GlobalExceptionHandler.java 3.3 KB
    │   │           ├── 📂 interceptor                       0 B
    │   │           │   ├── 📄 JwtTokenInterceptor.java    2.8 KB
    │   │           │   └── 📄 LoggingInterceptor.java     748 B
    │   │           ├── 📂 mapper                            0 B
    │   │           │   ├── 📄 AdminMapper.java            744 B
    │   │           │   ├── 📄 BookMapper.java             663 B
    │   │           │   ├── 📄 BorrowMapper.java           674 B
    │   │           │   └── 📄 ReaderMapper.java           483 B
    │   │           ├── 📂 service                           0 B
    │   │           │   ├── 📄 AdminService.java           344 B
    │   │           │   ├── 📄 BookService.java            414 B
    │   │           │   ├── 📄 BorrowService.java          460 B
    │   │           │   ├── 📂 Impl                          0 B
    │   │           │   │   ├── 📄 AdminServiceImpl.java   2.8 KB
    │   │           │   │   ├── 📄 BookServiceImpl.java    3.5 KB
    │   │           │   │   ├── 📄 BorrowServiceImpl.java  2.9 KB
    │   │           │   │   └── 📄 ReaderServiceImpl.java  4.2 KB
    │   │           │   └── 📄 ReaderService.java          486 B
    │   │           └── 📂 util                              0 B
    │   │               ├── 📄 Advice.java                 1.2 KB
    │   │               ├── 📄 JwtUtils.java               1.2 KB
    │   │               ├── 📄 MyUtils.java                1.2 KB
    │   │               └── 📄 ThreadLocalUtil.java        405 B
    │   └── 📂 resources                                     0 B
    │       ├── 📄 application-dev.yml                     721 B
    │       ├── 📄 application.yml                         650 B
    │       ├── 📄 banner.txt                              6.4 KB
    │       └── 📂 mapper                                    0 B
    │           ├── 📄 BorrowMapper.xml                    369 B
    │           └── 📄 ReaderMapper.xml                    448 B
    └── 📂 test                                              0 B
        └── 📂 java                                          0 B
            └── 📂 com                                       0 B
                └── 📂 clb                                   0 B
                    ├── 📄 BookApplicationTests.java       505 B
                    ├── 📄 BorrowTest.java                 744 B
                    └── 📄 TestRedis.java                  659 B

- `src/main/java/com/clb/`
  - `config`：配置文件
  - `constant`：枚举字段
  - `controller`：表现层
  - `domain`：实体类等
  - `exception`：异常类
  - `handle`：处理器类
  - `interceptor`：拦截器类
  - `mapper`：持久层
  - `service`：业务层
  - `util`：工具类
- `src/main/resources`
  - `mapper`：映射文件mapper
  - `application.yml`：配置文件
  - `banner.txt`：spring项目启动logo
- `src/test/`：测试类
- `pom.xml`：依赖管理

## 打包使用

> 将项目使用maven打成jar包后可以通过命令行执行jar包

```cmd
java -jar .\book-1.0.0.RELEASE.jar
```

> 可以修改端口号

```cmd
java -jar .\book-1.0.0.RELEASE.jar --server.port=8081
```

> ~也可以关闭redis~

```cmd
java -jar .\book-1.0.0.RELEASE.jar --server.port=8081 --spring.cache.type=none
```

> [!tip]
> 此项目对应的[微服务版本](https://github.com/caolib/cloud-book)

## 提交分析
![Alt](https://repobeats.axiom.co/api/embed/fff6dbaa9aa86bbe35a974910b89f89dd10a3383.svg "Repobeats analytics image")

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=caolib/book_management_system,caolib/vue3-vite&type=Timeline)](https://star-history.com/#caolib/book_management_system&caolib/vue3-vite&Timeline)
