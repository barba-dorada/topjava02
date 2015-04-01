package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * GKislin
 * 09.03.2015.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserMealRepositoryImpl.class);

    @Override
    public UserMeal save(UserMeal userMeal,int userId) {
        if(userMeal.getUser().getId()!=userId) throw LOG.getAccesViovationException("bad userId on save! userId:"+userId+"meal:"+userMeal);
        LOG.info("save " + userMeal);
        return userMeal;
    }

    // тут два пути. или на уровне запросов по id и userId к базе. или стандартный запрос + проверка полученного.
    @Override
    public boolean delete(int id,int userId) {
        LOG.info("delete " + id);
        return true;
    }

    // тут два пути. или на уровне запросов по id и userId к базе. или стандартный запрос + проверка полученного.
    @Override
    public UserMeal get(int id,int userId) {
        LOG.info("get " + id);
        return null;
    }

    // тут доступ на уровне запроса, проверяет база.
    @Override
    public List<UserMeal> getAll(int userId) {
        LOG.info("getAll " + userId);
        return Collections.emptyList();
    }

    @Override
    public void deleteAll(int userId) {

    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }


}
