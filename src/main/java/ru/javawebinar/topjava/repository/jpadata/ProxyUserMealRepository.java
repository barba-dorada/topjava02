package ru.javawebinar.topjava.repository.jpadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vad on 01.04.2015 19:55.
 */
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal um WHERE um.user.id=:userId and um.id=:id")
    int deleteById(@Param("id") int id,@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal um WHERE um.user.id=:id")
    int deleteAllByUserId(@Param("id") int id);



    @Query("SELECT um from UserMeal um WHERE um.user.id=:id order by um.dateTime desc ")
    List<UserMeal> getAllByUserId(@Param("id") int id);


    @Query("SELECT m from UserMeal m WHERE m.user.id=:userId " +
            " AND m.dateTime>=:after and m.dateTime<:before ORDER BY m.dateTime DESC"
    )
    List<UserMeal> getAllBetween(
            @Param("userId") int id,
            @Param("after") LocalDateTime start,
            @Param("before") LocalDateTime stop
    );


}
