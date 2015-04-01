package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    static DateTimeFormatter fmt=  DateTimeFormatter.ofPattern("yyyyMMdd");//.BASIC_ISO_DATE;

//    VALUES ('20150310','meal1',100,100000);
//    VALUES ('20150311','meal2',200,100000);
//    VALUES ('20150312','meal3',350,100000);

    public static UserMeal MEAL1= createUserMeal(100003, "20150310", "meal1", 100, UserTestData.USER);
    public static UserMeal MEAL2= createUserMeal(100004, "20150311", "meal2", 200, UserTestData.USER);
    public static UserMeal MEAL3= createUserMeal(100005, "20150312", "meal3", 350, UserTestData.USER);
    public static UserMeal MEAL4= createUserMeal(null, "20150314", "meal4", 123, UserTestData.USER);


    public static void main(String[] args) {
        System.out.println(LocalDate.parse("20150310", fmt));
    }

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(
            new Function<UserMeal, String>() {
                @Override
                public String apply(UserMeal meal) {
                    return meal.toString();
                }
            });

    static UserMeal createUserMeal(Integer id, String sDateTime, String descr, int callories, User user){
        LocalDateTime td = str2DT(sDateTime);
        UserMeal userMeal=new UserMeal(id, td,descr,callories);
        userMeal.setUser(user);
        return userMeal;
    }

    public static LocalDateTime str2DT(String sDateTime) {
        return LocalDate.parse(sDateTime, fmt).atStartOfDay();
    }

}
