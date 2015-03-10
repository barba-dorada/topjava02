package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminUserRestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            String beans = String.join("\n", appCtx.getBeanDefinitionNames());
            System.out.println("\n" + beans + "\n");
            AdminUserRestController adminController = appCtx.getBean(AdminUserRestController.class);
//            adminController.delete(7);
//            adminController.getByMail("dummy");

            UserMealRestController userMealRestController=appCtx.getBean(UserMealRestController.class);

            userMealRestController.setUserId(333);
            
            UserMeal userMeal = userMealRestController.getNew();
            userMeal.setDescription("meal 1");
            userMeal.setCalories(100);
            userMealRestController.delete(8);
            userMealRestController.getAll();
            //userMealRestController.get(1);
            userMealRestController.create(userMeal);
            userMeal.setDescription("meal 2");
            userMealRestController.update(userMeal);

            
        }
    }
}

/* output......

"C:\Program Files\Java\jdk1.8.0_31\bin\java" -Dvisualvm.id=80102919451701 -Didea.launcher.port=7538 "-Didea.launcher.bin.path=C:\dev\JetBrains\IntelliJ IDEA 14.0.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_31\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\rt.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_31\jre\lib\ext\zipfs.jar;C:\dev\projects\topjava\target\classes;C:\Users\adm\.m2\repository\org\slf4j\slf4j-api\1.7.7\slf4j-api-1.7.7.jar;C:\Users\adm\.m2\repository\org\slf4j\jcl-over-slf4j\1.7.7\jcl-over-slf4j-1.7.7.jar;C:\Users\adm\.m2\repository\org\slf4j\jul-to-slf4j\1.7.7\jul-to-slf4j-1.7.7.jar;C:\Users\adm\.m2\repository\ch\qos\logback\logback-classic\1.1.2\logback-classic-1.1.2.jar;C:\Users\adm\.m2\repository\ch\qos\logback\logback-core\1.1.2\logback-core-1.1.2.jar;C:\Users\adm\.m2\repository\org\springframework\spring-context\4.1.5.RELEASE\spring-context-4.1.5.RELEASE.jar;C:\Users\adm\.m2\repository\org\springframework\spring-aop\4.1.5.RELEASE\spring-aop-4.1.5.RELEASE.jar;C:\Users\adm\.m2\repository\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;C:\Users\adm\.m2\repository\org\springframework\spring-beans\4.1.5.RELEASE\spring-beans-4.1.5.RELEASE.jar;C:\Users\adm\.m2\repository\org\springframework\spring-core\4.1.5.RELEASE\spring-core-4.1.5.RELEASE.jar;C:\Users\adm\.m2\repository\org\springframework\spring-expression\4.1.5.RELEASE\spring-expression-4.1.5.RELEASE.jar;C:\dev\JetBrains\IntelliJ IDEA 14.0.2\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain ru.javawebinar.topjava.SpringMain
INFO  ClassPathXmlApplicationContext [AbstractApplicationContext.java:510] Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@61dc03ce: startup date [Tue Mar 10 13:13:26 GMT+03:00 2015]; root of context hierarchy
INFO  XmlBeanDefinitionReader [XmlBeanDefinitionReader.java:317] Loading XML bean definitions from class path resource [spring/spring-app.xml]

org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalRequiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
mockUserMealRepositoryImpl
mockUserRepositoryImpl
userMealServiceImpl
userServiceImpl
userMealRestController
adminUserRestController
userRestController
org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor
org.springframework.context.annotation.ConfigurationClassPostProcessor.enhancedConfigurationProcessor

INFO  UserMealRestController [LoggerWrapper.java:28] delete 8
INFO  UserMealServiceImpl [LoggerWrapper.java:28] delete 8
INFO  MockUserMealRepositoryImpl [LoggerWrapper.java:28] delete 8
INFO  UserMealRestController [LoggerWrapper.java:28] getAll
INFO  UserMealServiceImpl [LoggerWrapper.java:28] getByUserId 0
INFO  MockUserMealRepositoryImpl [LoggerWrapper.java:28] getById 0
INFO  UserMealRestController [LoggerWrapper.java:28] create UserMeal{userId=0, dateTime=2015-03-10T13:13:26.988, description='meal 1', calories=100} BaseEntity{id=0}
INFO  UserMealServiceImpl [LoggerWrapper.java:28] save UserMeal{userId=0, dateTime=2015-03-10T13:13:26.988, description='meal 1', calories=100} BaseEntity{id=0}
INFO  MockUserMealRepositoryImpl [LoggerWrapper.java:28] save UserMeal{userId=0, dateTime=2015-03-10T13:13:26.988, description='meal 1', calories=100} BaseEntity{id=0}
INFO  UserMealRestController [LoggerWrapper.java:28] update UserMeal{userId=0, dateTime=2015-03-10T13:13:26.988, description='meal 2', calories=100} BaseEntity{id=0}
INFO  UserMealServiceImpl [LoggerWrapper.java:28] update UserMeal{userId=0, dateTime=2015-03-10T13:13:26.988, description='meal 2', calories=100} BaseEntity{id=0}
INFO  MockUserMealRepositoryImpl [LoggerWrapper.java:28] save UserMeal{userId=0, dateTime=2015-03-10T13:13:26.988, description='meal 2', calories=100} BaseEntity{id=0}
INFO  ClassPathXmlApplicationContext [AbstractApplicationContext.java:862] Closing org.springframework.context.support.ClassPathXmlApplicationContext@61dc03ce: startup date [Tue Mar 10 13:13:26 GMT+03:00 2015]; root of context hierarchy

Process finished with exit code 0
 */
