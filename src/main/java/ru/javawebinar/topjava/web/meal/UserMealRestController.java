package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.exception.ExceptionUtil.checkAccess;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealRestController.class);

    @Autowired
    private UserMealService service;

    public UserMeal getNew(){
        UserMeal userMeal=new UserMeal();
        userMeal.setDateTime(LocalDateTime.now());
        userMeal.setCalories(0);
        userMeal.setDescription("meal");
        userMeal.setUserId(LoggedUser.id());
        userMeal.setId(0);
        return userMeal;
    }
    
    public List<UserMeal> getAll() {
        LOG.info("getAll");
        return service.getByUserId(LoggedUser.id());
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        UserMeal userMeal=service.get(id);
        checkAccess(userMeal);
        return userMeal;
    }

    public UserMeal create(UserMeal userMeal) {
        LOG.info("create " + userMeal);
        checkAccess(userMeal);
        return service.save(userMeal);
    }

    public void delete(int id) {
        checkAccess(service.get(id));
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(UserMeal userMeal) {
        checkAccess(userMeal);
        LOG.info("update " + userMeal);
        service.update(userMeal);
    }

}
