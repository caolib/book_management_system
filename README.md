[![Typing SVG](https://readme-typing-svg.herokuapp.com?font=cascadia+code&size=38&duration=3500&pause=1000&color=00ADFF&center=true&vCenter=true&random=false&width=1000&height=100&lines=Book+lending+management+system;图书借阅管理系统)](https://git.io/typing-svg)

&emsp;&emsp;

![springboot](https://img.shields.io/badge/springboot-v3.0.9-%236DB33F?style=flat&logo=springboot&logoColor=236DB33F&labelColor=white)
![maven](https://img.shields.io/badge/Maven-v3.9.5-blue?style=flat&logo=apachemaven&logoColor=red&labelColor=white)
![mybatisplus](https://img.shields.io/badge/MybatisPlus-v3.5.3.1-red?style=flat&labelColor=white)
![mysql](https://img.shields.io/badge/MySQL-v8.2.0-blue?style=flat&logo=mysql&logoColor=blue&labelColor=white)
![redis](https://img.shields.io/badge/Redis-v7.0.12-red?style=flat&logo=redis&logoColor=%23DC382D&labelColor=white)
![GitHub Release](https://img.shields.io/github/v/release/tankingcao/java_design?include_prereleases&sort=date&display_name=release&style=flat&labelColor=red&cacheSeconds=3600)
![下载量](https://img.shields.io/github/downloads/caolib/book_management_system/total.svg)


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
> - 注意前后端一般是同时修改的，必须匹配版本，没有特别需求直接使用最新的

## 使用IDEA快速开发

如果你使用IDEA开发，可以按照以下步骤快速进行使用，你也可以在[**我的网站**](https://bin-sites.pages.dev/book)查看下面教程

### 后端项目

> [!WARNING]
>
> 如果你没有安装git，可以手动下载[项目压缩包](https://github.com/caolib/book_management_system/archive/refs/heads/master.zip)然后解压后用IDEA打开

1. 复制项目地址

```bash
git@github.com:caolib/book_management_system.git
```

2. 打开IDEA，点击右上角**从VCS获取**，粘贴项目地址，选取一个合适的目录，然后点击克隆

<img src="https://s2.loli.net/2024/12/30/fm7o3L4EDTVQZAR.png" alt="image-20241230113626675" style="zoom: 50%;" />

#### 设置Maven

项目打开后先**打开设置**检查maven配置是否正确，如果你没有自己额外下载maven，可以使用IDEA捆绑的maven

<img src="https://s2.loli.net/2024/12/30/bcy9G1Bo6qKnwWL.png" alt="image-20241230115029577" style="zoom:67%;" />

设置好后打开maven窗口刷新，无报错则成功导入依赖

![image-20241230115352158](https://s2.loli.net/2024/12/30/FlNtrXiGqSzLTC3.png)

#### 设置Java版本

在文件选项，点击项目结构

![image-20241230115812170](https://s2.loli.net/2024/12/30/78ZGtsJQ1uWwiqD.png)

本项目使用Java17，没有安装可以下载一个

![image-20241230115937252](https://s2.loli.net/2024/12/30/ct17Yg49obIqdf5.png)

#### 启动项目

这些都无误就可以启动项目了

![image-20241230120342131](https://s2.loli.net/2024/12/30/CnyfmkMYhcbjgzO.png)

#### 配置数据库

##### Mysql配置

###### 连接数据库

1. 先下载sql文件，[Releases · caolib/book_management_system](https://github.com/caolib/book_management_system/releases)
2. 创建一个mysql数据源

![image-20241230120647502](https://s2.loli.net/2024/12/30/jJfCoGuQl8zdXKt.png)

> [!IMPORTANT]
>
> 1. 主机：你的mysql数据库地址，如果安装在本地，直接填localhost，如果安装在虚拟机上可以先使用`ip addr`命令查看虚拟机地址，然后填写虚拟机地址（注意，如果虚拟机重启，这个地址大概率会变化）
>
> ![image-20241230121438535](https://s2.loli.net/2024/12/30/ojKx2nCA1gmJeZ3.png)
>
> 2. 端口：如果没修改过，默认是3306
>
> > [!caution]
> >
> > 如果在虚拟机上，需要开放防火墙端口这里顺便打开了redis的端口6379
> >
> > ```bash
> > sudo firewall-cmd --zone=public --add-port=3306/tcp --permanent
> > sudo firewall-cmd --zone=public --add-port=6379/tcp --permanent
> > ```
>
> 3. 用户名，密码：按照自己设置的填写就可以
> 4. 点击测试连接，成功后点击确定



![image-20241230120920597](https://s2.loli.net/2024/12/30/Fjw8QLcZ1eT4zMb.png)

###### 导入数据库

右键刚创建的数据源，选择运行sql脚本，然后选择下载的sql文件就可以了

![image-20241230123701850](https://s2.loli.net/2024/12/30/iIlEFJ1D5P89eaX.png)

导入后，选择`java_design`架构就可以看到导入的表了，**应该是有四张表和一个视图**

##### Redis配置

###### 连接数据库

同样创建一个redis数据源

![image-20241230124300262](https://s2.loli.net/2024/12/30/IvGRhDyglKxbPqM.png)

redis默认没有密码，只需要填写主机号，同mysql，然后点击测试连接，成功后点击确定

![image-20241230124346981](https://s2.loli.net/2024/12/30/UsvGWhiOnxAdpuP.png)

#### 修改配置文件

打开`application-dev.yml`文件，修改host（主机），用户名、密码等，修改后重启项目

![image-20241230130628980](https://s2.loli.net/2024/12/30/jQ63Re19UYduLPM.png)

#### 测试接口

项目的[API](https://app.apifox.com/project/5694188)，测试相应接口

![image-20241230134927055](https://s2.loli.net/2024/12/30/kcLVWdvb7loOeYA.png)

### [前端项目](https://github.com/caolib/vue3-vite)

#### 克隆项目

下载或clone到本地，这里使用VSCode打开(随便用什么打开都行)

#### 检查配置

打开`vite.config.js`文件,查看后端服务地址，默认是本地8080，如果无误则无需修改

```js
  server: {
    host: "localhost",
    port: 5173,
    proxy: {
      "/api": {
        target: "http://localhost:8080", //后端服务地址
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  }
```

#### 安装依赖

打开控制台，运行`npm install`，如果报错，可以以管理员方式打开控制台再运行

> [!CAUTION]
>
> 使用npm需要安装Nodejs，版本不要太低，18.x和20.x都可以

```sh
npm install
```

#### 启动项目

```sh
npm run dev
```

默认运行在`http://localhost:5173`，账号和密码可以在mysql表中查看

---


### 关于

<img align='right' src="https://s2.loli.net/2024/12/30/qPgJilDEutcjFCO.png" />❓ 有问题可以在[Issues](https://github.com/caolib/book_management_system/issues)提问

⭐ 如果对你有帮助的话请点个star

☕ 请我 [喝杯咖啡](https://bin-sites.pages.dev/sponsor/)

📧 联系我 [1265501579@qq.com](mailto:1265501579@qq.com)


