package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
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

    @Autowired
    private UserMealService service;

    public UserMeal getNew(){
        LOG.info("getNew");
        return service.getNew(LoggedUser.id());
    }
    
    public List<UserMeal> getAll() {
        LOG.info("getAll");
        return service.getAll(LoggedUser.id());
    }

    public List<UserMeal> getAllFromTo(LocalDateTime from,LocalDateTime to) {
        LOG.info("getAllFromTo");
        return service.getAll(from,to,LoggedUser.id());
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        UserMeal userMeal=service.get(id,LoggedUser.id());
        return userMeal;
    }

    public UserMeal create(UserMeal userMeal) {
        LOG.info("create " + userMeal);
        return service.save(userMeal,LoggedUser.id());
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id,LoggedUser.id());
    }

    public void deleteAll(){
        LOG.info("deleteAll");
        service.deleteAll(LoggedUser.id());
    }

    public void update(UserMeal userMeal) {
        LOG.info("update " + userMeal);
        service.update(userMeal,LoggedUser.id());
    }

}
