package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.javawebinar.topjava.UserTestData.*;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;


public abstract class UserServiceTest extends ServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void testSave() throws Exception {
        TestUser tu = new TestUser("New", "new@gmail.com", "newPass", Role.ROLE_USER);
        User created = service.save(tu.asUser());
        tu.setId(created.getId());
        MATCHER.assertListEquals(Arrays.asList(ADMIN, tu, USER, USER2), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new TestUser("Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER).asUser());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(BaseEntity.START_SEQ);
        MATCHER.assertListEquals(Arrays.asList(ADMIN, USER2), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(BaseEntity.START_SEQ);
        MATCHER.assertEquals(USER, user);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        MATCHER.assertEquals(USER, user);

    }

    @Test
    public void testGetAll() throws Exception {
        List<User> all = service.getAll();
        MATCHER.assertListEquals(Arrays.asList(ADMIN, USER, USER2), all);
    }

    @Test
    public void testUpdate() throws Exception {
        TestUser updated = new TestUser(USER);
        updated.setName("UpdatedName");
        service.update(updated.asUser());
        MATCHER.assertEquals(updated, service.get(BaseEntity.START_SEQ));
    }
}