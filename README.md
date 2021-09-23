<div align="center">
    <h1>
        基于SSM搭建的个人博客
    </h1>
</div>
<h2 id="top">目录</h2>

#### <a href="#one">一、创建数据库和表</a>

#### <a href="#tow">二、搭建开发环境</a>

#### <a href="#three">三、初始化项目并引入静态页面</a>



<h2 id="one">一、创建表和数据库</h2>

#### 1.1、创建数据库

<div align="center">
<img src="http://tva1.sinaimg.cn/large/006gOimwly1guksc1xwj6j60bi06kdgp02.jpg"/>

</div>

#### 2.2、创建表

1. ##### 博主表

   <img src="http://tva1.sinaimg.cn/large/006gOimwly1guksidbxuij610p09qaex02.jpg"/>

2. ##### 博客类型表

   <img src="http://tva1.sinaimg.cn/large/006gOimwly1guksd2h0ygj611007htbw02.jpg"/>

3. 博客表

   <img src="http://tva1.sinaimg.cn/large/006gOimwly1guksirhmsyj610v0acte902.jpg"/>

4. 评论表

   <img src="http://tva1.sinaimg.cn/large/006gOimwly1guksdrjxbcj610y09zwj102.jpg"/>

5. 友情链接表

   <img src="http://tva1.sinaimg.cn/large/006gOimwly1guksejpy1mj610v09878702.jpg"/>

   至此 ，数据表基本创建完毕！！！

<h2 id="two">二、搭建开发环境</h2>

#### 2.1、开发工具

`IDEA`,`Maven`,`Git`,`Mysql`

#### 2.2、开发环境

`Spring`,`SpringMVC`,`Mybatis`

<h2 id="three">三、初始化项目并引入静态页面</h2>

#### 3.1配置`web.xml`

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
  <display-name>Archetype Created Web Application</display-name>

  <!--spring配置文件-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring/applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <listener>
    <listener-class>com.louis.blog.service.impl.InitComponent</listener-class>
  </listener>

  <!--springmvc配置文件-->
  <servlet>
    <servlet-name>blog</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring/spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
    <async-supported>true</async-supported>
  </servlet>
  <servlet-mapping>
    <servlet-name>blog</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>blog</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>blog</servlet-name>
    <url-pattern>*.html</url-pattern>
  </servlet-mapping>


  <!--shiro过滤器-->
  <filter>
    <filter-name>shiroFilter</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    <init-param>
      <!--该值缺省weifalse，表示生命周期由SpringApplicationContext管理，设置为true表示由ServletContainer管理-->
      <param-name>targetFilterLifecycle</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>shiroFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!--中文编码过滤-->
  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
</web-app>
```

#### 3.2、配置`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:jee="http://www.springframework.org/schema/jee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
                           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
                           http://www.springframework.org/schema/p http://www.springframework.org/schema/p/spring-p.xsd
                           http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee.xsd">


    <!--包扫描-->
    <context:component-scan base-package="com.louis.blog">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--配置数据源-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url"
                  value="jdbc:mysql://localhost:3306/db_blog?useUnicode=true&amp;characterEncoding=utf-8&amp;"></property>
        <property name="username" value="root"></property>
        <property name="password" value="123456"></property>
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
    </bean>

    <!--配置mybatis的sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"></property>
        <property name="dataSource" ref="dataSource"></property>
        <property name="mapperLocations" value="classpath:com/louis/blog/dao/*.xml "></property>
    </bean>

    <!--DAO接口所在的包，spring会自动扫描这个包下面的类-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.louis.blog.dao"></property>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>

    <!--事务管理-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--自定义realm-->
    <bean id="myRealm" class="com.louis.blog.realm.MyRealm">

    </bean>
    <!--安全管理-->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="myRealm"></property>
    </bean>

    <!--shiro过滤器-->
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <!--shiro权限管理接口-->
        <property name="securityManager" ref="securityManager"></property>
        <!--身份认证跳转的页面-->
        <property name="loginUrl" value="/login.jsp"></property>
        <!---->
        <property name="filterChainDefinitions">
            <value>
                /login = anon
                /admin/** = authc
            </value>
        </property>
    </bean>

    <!--spring和shiro集成-->
    <!--保证实现了shiro内部lifecycle含糊bean的执行-->
    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor">

    </bean>

    <!--开启shiro注解-->
    <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
          depends-on="lifecycleBeanPostProcessor"></bean>
    <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
        <property name="securityManager" ref="securityManager"></property>
    </bean>

    <!--配置事务切面-->
    <aop:config>
        <aop:pointcut id="serviceOperation" expression="execution(* com.louis.blog.service.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="serviceOperation"></aop:advisor>
    </aop:config>

    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="insert*" propagation="REQUIRED"/>
            <tx:method name="update*" propagation="REQUIRED"/>
            <tx:method name="edit*" propagation="REQUIRED"/>
            <tx:method name="save*" propagation="REQUIRED"/>
            <tx:method name="add*" propagation="REQUIRED"/>
            <tx:method name="new*" propagation="REQUIRED"/>
            <tx:method name="set*" propagation="REQUIRED"/>
            <tx:method name="remove*" propagation="REQUIRED"/>
            <tx:method name="delete*" propagation="REQUIRED"/>
            <tx:method name="change*" propagation="REQUIRED"/>
            <tx:method name="check*" propagation="REQUIRED"/>
            <tx:method name="get*" propagation="REQUIRED"/>
            <tx:method name="find*" propagation="REQUIRED"/>
            <tx:method name="load*" propagation="REQUIRED"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
</beans>
```

#### 3.3、配置`spring-mvc.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc  http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <!--包扫描-->
    <context:component-scan base-package="com.louis.blog" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <mvc:default-servlet-handler/>
    <mvc:annotation-driven></mvc:annotation-driven>

    <!--视图解析器-->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>

    <!--文件下载-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"></property>
        <property name="maxUploadSize" value="10000000"></property>
    </bean>
</beans>
```

#### 3.4、配置`log4j.properties`

```xml
#输出位置
log4j.rootLogger=DEBUG,Console

log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPatter=%d [%t] [%p] [%c] - %m%n

log4j.logger.java.sql.ResultSet=INFO
log4j.logger.org.apche=INFO
log4j.logger.java.sql.Connection=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PrepareStatement=DEBUG
```

<h2 id="four">四、业务实现</h2>

#### 4.1、登录业务

编写`md5`加密工具类生成用户密码：

```java
package com.louis.blog.util;

import java.awt.dnd.MouseDragGestureRecognizer;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class CryptographyUtil {

    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("123456","admin"));
    }
}
```

登录实现：

```java
@RequestMapping("/login")
public String login(Blogger blogger, HttpServletRequest request){
    String username = blogger.getUsername();
    String password = blogger.getPassword();
    String pw = CryptographyUtil.md5(password,username);
    //get subject
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(username,pw);
    try{
        //传递token给shiro的realm
        subject.login(token);
        return "redirect:/admin/main.jsp";
    }catch (Exception e){
        e.printStackTrace();
        request.setAttribute("blogger",blogger);
        request.setAttribute("errorInfo","用户名/密码错误");
    }
    return "login";
}
```

用shiro验证权限：

```java
//权限验证
//token:令牌，基于用户名密码的令牌
@Override
protected AuthenticationInfo doGetAuthenticationInfo(
        AuthenticationToken authenticationToken) throws AuthenticationException {
    String username = (String) authenticationToken.getPrincipal();
    Blogger blogger = bloggerService.getBloggerByUsername(username);
    if(blogger != null){
        SecurityUtils.getSubject().getSession().setAttribute("currentUser",blogger);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogger.getUsername(),
                blogger.getPassword(),getName());
        return authenticationInfo;
    }
    return null;
}
```

