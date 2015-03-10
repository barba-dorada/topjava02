package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

/**
 * GKislin
 * 06.03.2015.
 */

@Service
public class UserMealServiceImpl implements UserMealService {

    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServiceImpl.class);

    @Autowired
    private UserMealRepository repository;

    public UserMeal save(UserMeal userMeal) {
        LOG.info("save " + userMeal);
        return repository.save(userMeal);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        ExceptionUtil.check(repository.delete(id), id);
    }

    public UserMeal get(int id) throws NotFoundException {
        LOG.info("get " + id);
        return ExceptionUtil.check(repository.get(id), id);
    }

    public List<UserMeal> getByUserId(int userId) throws NotFoundException {
        LOG.info("getByUserId " + userId);
        return ExceptionUtil.check(repository.getByUserId(userId), "userId=" + userId);
    }
// а это тут нужно?
    public List<UserMeal> getAll() {
        LOG.info("getAll");
        return repository.getAll();
    }

    public void update(UserMeal userMeal) throws NotFoundException {
        LOG.info("update " + userMeal);
        ExceptionUtil.check(repository.save(userMeal), userMeal.getId());

    }


}
