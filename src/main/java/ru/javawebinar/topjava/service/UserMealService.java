package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 09.03.2015.
 */
public interface UserMealService {
    public UserMeal save(UserMeal user);

    public void delete(int id) throws NotFoundException;

    public UserMeal get(int id) throws NotFoundException;

    public List<UserMeal> getByUserId(int userId) throws NotFoundException;

    public void  deleteByUserId(int userId);

    public List<UserMeal> getAll();

    public void update(UserMeal userMeal) throws NotFoundException;

    public List<UserMeal> getAll(LocalDateTime from,LocalDateTime to);
}
