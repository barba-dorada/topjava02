package ru.javawebinar.topjava;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
      /*  try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
            UserMealRestController adminController = appCtx.getBean(UserMealRestController.class);
            adminController.delete(7);
        }
*/

        try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
            ctx.getEnvironment().setActiveProfiles("postgres", "jpadata");
            ctx.load("spring/spring-app.xml", "spring/spring-db.xml");
            ctx.refresh();

            EntityManagerFactory entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
            EntityManager em = entityManagerFactory.createEntityManager();

            print(ctx);

            PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
            System.out.println(persistenceUtil);


            Map<String, String> prop = new HashMap<String, String>();
            prop.put("javax.persistence.schema-generation.scripts.create-target", "c:/dev/sampleCreate.ddl");
            prop.put("javax.persistence.schema-generation.scripts.drop-target", "c:/dev/sampleDrop.ddl");
            Persistence.generateSchema("default", null);
            //PersistenceUtil pu = Persistence.getPersistenceUtil();

           /* UserMealRestController adminController = ctx.getBean(UserMealRestController.class);
            UserService us=ctx.getBean(UserService.class);

            System.out.println(us.getAll());

            UserMealService userMealService=ctx.getBean(UserMealService.class);
            System.out.println(userMealService.getAll(100000));*/
        }

    }

    private static void print(GenericXmlApplicationContext ctx) {
        String sss = String.join("\n", ctx.getBeanDefinitionNames());
        System.out.println("\n==============\n" + sss + "\n=================\n");
    }
}
