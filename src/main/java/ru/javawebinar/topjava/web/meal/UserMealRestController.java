package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealRestController.class);
    private int userId;

    @Autowired
    private UserMealService service;

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public UserMeal getNew(){
        UserMeal userMeal=new UserMeal();
        userMeal.setDateTime(LocalDateTime.now());
        userMeal.setCalories(0);
        userMeal.setDescription("meal");
        userMeal.setUserId(userId);
        userMeal.setId(0);
        return userMeal;
    }
    
    public List<UserMeal> getAll() {
        LOG.info("getAll");
        return service.getByUserId(userId);
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public UserMeal create(UserMeal userMeal) {
        LOG.info("create " + userMeal);
        //userMeal.setUserId(userId);
        return service.save(userMeal);
    }

    public void delete(int id) {
        //todo ?check userId on delete
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(UserMeal userMeal) {
        //todo ?check userId on update
        LOG.info("update " + userMeal);
        service.update(userMeal);
    }

}
