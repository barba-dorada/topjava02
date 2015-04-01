package ru.javawebinar.topjava;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

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
            ctx.getEnvironment().setActiveProfiles("postgres", "jpa");
            ctx.load("spring/spring-app.xml", "spring/spring-db.xml");
            ctx.refresh();
            String sss=String.join("\n", ctx.getBeanDefinitionNames());
            System.out.println("\n" + sss + "\n");
            UserMealRestController adminController = ctx.getBean(UserMealRestController.class);
            UserService us=ctx.getBean(UserService.class);

            System.out.println(us.getAll());

            UserMealService userMealService=ctx.getBean(UserMealService.class);
            System.out.println(userMealService.getAll(100000));
        }

    }
}
