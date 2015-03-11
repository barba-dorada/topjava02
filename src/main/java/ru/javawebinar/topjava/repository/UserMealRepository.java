package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    UserMeal get(int id, int userId);

    List<UserMeal> getAll(int userId);

    List<UserMeal> getFromTo(LocalDateTime from, LocalDateTime to, int userId);


}
