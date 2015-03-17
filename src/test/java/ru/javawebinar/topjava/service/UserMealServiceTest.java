package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.AccessViolationException;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER2;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal =service.get(100004,USER.getId());
        MealTestData.MATCHER.assertEquals(MEAL2,userMeal);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL1.getId(),USER.getId());
        List<UserMeal> all = service.getAll(USER.getId());
        MealTestData.MATCHER.assertListEquals(all, Arrays.asList( MEAL2, MEAL3));

    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1,USER.getId());
    }

    @Test
    public void testGetBetween() throws Exception {
        List<UserMeal> list = service.getBetween(str2DT("20150310"), str2DT("20150311"),USER.getId());
        MealTestData.MATCHER.assertListEquals(Arrays.asList( MEAL1,MEAL2),list );
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserMeal> all = service.getAll(USER.getId());
        MealTestData.MATCHER.assertListEquals(all, Arrays.asList(MealTestData.MEAL1, MealTestData.MEAL2, MEAL3));
    }

    @Test
    public void testDeleteAll() throws Exception {
        service.deleteAll(USER.getId());
        List<UserMeal> all = service.getAll(USER.getId());
        MealTestData.MATCHER.assertListEquals(all, Arrays.asList());
    }

    @Test
    public void testUpdate() throws Exception {
        MEAL2.setDescription("updated meal2");
        service.update(MEAL2,USER.getId());
        MealTestData.MATCHER.assertEquals(MEAL2,service.get(MEAL2.getId(),USER.getId()));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal created=service.save(MEAL4,USER.getId());
        MEAL4.setId(created.getId());
        MealTestData.MATCHER.assertListEquals(Arrays.asList(MEAL1,MEAL2,MEAL3,MEAL4), service.getAll(USER.getId()));
    }
    @Test(expected = AccessViolationException.class)
    public void testAccessViolationOnDelete(){
        service.delete(100006,100000);
    }

    @Test(expected = AccessViolationException.class)
    public void testAccessViolationOnSave(){
        service.save(MEAL4, USER2.getId());
    }
}