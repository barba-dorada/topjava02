package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */

@Service
public class UserMealServiceImpl implements UserMealService {

    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServiceImpl.class);

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        LOG.info("save " + userMeal);
        return repository.save(userMeal,  userId);
    }

    @Override
    public void delete(int id, int userId) {
        LOG.info("delete " + id);
        ExceptionUtil.check(repository.delete(id,  userId), id);
    }

    @Override
    public UserMeal get(int id, int userId) throws NotFoundException {
        LOG.info("get " + id);
        return ExceptionUtil.check(repository.get(id,  userId), id);
    }

    @Override
    public List<UserMeal> getAll(int userId) throws NotFoundException {
        LOG.info("getByUserId " + userId);
        return ExceptionUtil.check(repository.getAll(userId), "userId=" + userId);
    }

    @Override
    public void deleteAll(int userId) {
        LOG.info("deleteByUserId " + userId);
        List<UserMeal> userMealList = getAll(userId);
        for (UserMeal userMeal:userMealList){
            delete(userMeal.getId(),userId);
        }
    }

    @Override
    public void update(UserMeal userMeal, int userId) throws NotFoundException {
        LOG.info("update " + userMeal);
        ExceptionUtil.check(repository.save(userMeal, userId), userMeal.getId());

    }


    @Override
    public List<UserMeal> getAll(LocalDateTime from,LocalDateTime to, int userId) {
        LOG.info("getAllFromTo");
        return  repository.getFromTo(from,to, userId);
    }

    @Override
    public UserMeal getNew(int userId) {
        UserMeal userMeal=new UserMeal();
        userMeal.setDateTime(LocalDateTime.now());
        userMeal.setCalories(0);
        userMeal.setDescription("meal");
        userMeal.setUserId(userId);
        userMeal.setId(0);
        return userMeal;
    }


}
