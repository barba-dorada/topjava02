package ru.javawebinar.topjava.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by vad on 01.04.2015 11:50.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles({"postgres","jpa"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaUserMealServiceTest extends UserMealServiceTest {
}
