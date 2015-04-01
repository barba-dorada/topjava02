package ru.javawebinar.topjava.repository.jpadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.AccessViolationException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vad on 01.04.2015 16:14.
 */
@Repository
public class JpaDataUserMealRepositoryImpl implements UserMealRepository {

    @Autowired
    ProxyUserMealRepository proxy;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.getUser().getId() != userId)
            throw new AccessViolationException("AssessViolation! bad userId:" + userId + "for " + userMeal);

        return proxy.save(userMeal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.deleteById(id,userId)==1;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal userMeal = proxy.findOne(id);
        if(userMeal.getUser().getId()!=userId)
            throw new AccessViolationException("AssessViolation! bad userId:" + userId + "for " + userMeal);
        return userMeal;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.getAllByUserId(userId);
    }

    @Override
    public void deleteAll(int userId) {
        proxy.deleteAllByUserId(userId);

    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getAllBetween(userId,startDate,endDate);
    }
}
