package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.model.converter.LocalDateTimeConverter;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * GKislin
 * 06.03.2015.
 */

@NamedQueries({
        @NamedQuery(name = UserMeal.GET, query = "SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT m FROM UserMeal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),
        @NamedQuery(name = UserMeal.DELETE_ALL, query = "DELETE FROM UserMeal i WHERE i.user.id=:userId"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = UserMeal.GET_BETWEEN,
                query = "SELECT m from UserMeal m WHERE m.user.id=:userId " +
                        " AND m.dateTime>=:after and m.dateTime<:before ORDER BY m.dateTime DESC"),

//        @NamedQuery(name = UserMeal.UPDATE, query = "UPDATE UserMeal m SET m.dateTime = :datetime, m.calories= :calories," +
//                "m.description=:desc where m.id=:id and m.user.id=:userId")
})

@Entity
@Table(name = "meals")
public class UserMeal extends BaseEntity {
    public static final String GET = "UserMeal.get";
    public static final String ALL_SORTED = "UserMeal.getAll";
    public static final String DELETE = "UserMeal.delete";
    public static final String DELETE_ALL = "UserMeal.deleteAll";
    public static final String GET_BETWEEN = "UserMeal.getBetween";

    @Column(name = "date_time", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime dateTime;

    @Column(nullable = false)
    protected String description;

    protected int calories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    public UserMeal() {
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String meal) {
        this.description = meal;
    }

    public int getCalories() {
        return calories;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Meal(" + id + ", " + TimeUtil.toString(dateTime) + ", '" + description + "', calories:" + calories + ", iserId:" + user.getId() + ')';
    }

    public void setUser(User user) {
        this.user = user;
    }

}
