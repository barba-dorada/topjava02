package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * GKislin
 * 09.03.2015.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserMealRepositoryImpl.class);



    
    @Override
    public UserMeal save(UserMeal userMeal) {
        LOG.info("save " + userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return true;
    }

    @Override
    public UserMeal get(int id) {
        LOG.info("get " + id);
        return null;
    }

    @Override
    public List<UserMeal> getByUserId(int userId) {
        LOG.info("getById " + userId);
        return Collections.emptyList();
    }

    @Override
    public List<UserMeal> getAll() {
        LOG.info("getAll");
        return Collections.emptyList();
    }
}
