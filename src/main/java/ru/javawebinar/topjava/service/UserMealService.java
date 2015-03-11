package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 09.03.2015.
 */
public interface UserMealService {
    public UserMeal save(UserMeal user,  int userId);

    public void delete(int id, int userId) throws NotFoundException;

    public UserMeal get(int id, int userId) throws NotFoundException;

    //public List<UserMeal> getByUserId(int userId) throws NotFoundException;

    public void  deleteAll(int userId);

    public List<UserMeal> getAll( int userId);

    public void update(UserMeal userMeal, int userId) throws NotFoundException;

    public List<UserMeal> getAll(LocalDateTime from,LocalDateTime to, int userId);
    public UserMeal getNew(int userId);
}
