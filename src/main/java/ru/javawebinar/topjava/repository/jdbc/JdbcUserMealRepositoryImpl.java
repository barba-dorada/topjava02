package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.AccessViolationException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(JdbcUserMealRepositoryImpl.class);
    @Autowired
    UserRepository userRepository;

/*    private final RowMapper<UserMeal> ROW_MAPPER = new RowMapper<UserMeal>() {
        @Override
        public UserMeal mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserMeal userMeal=new UserMeal(
                    rs.getInt("id"),
                    rs.getTimestamp("date_time").toLocalDateTime(),
                    rs.getString("description"),
                    rs.getInt("calories"));
            userMeal.setUser(userRepository.get(rs.getInt("user_id")));
            return userMeal;
        }
    };*/


    private UserMeal mapUserMeal(ResultSet rs, int rowNum) throws SQLException {
        UserMeal userMeal = new UserMeal(
                rs.getInt("id"),
                rs.getTimestamp("date_time").toLocalDateTime(),
                rs.getString("description"),
                rs.getInt("calories"));
        userMeal.setUser(userRepository.get(rs.getInt("user_id")));
        return userMeal;
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUserMeal;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertUserMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }


    ///////////////////
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        LOG.info("save {} for user {}", userMeal, userId);

        if (userMeal.getUser().getId() != userId)
            throw new AccessViolationException("AssessViolation! bad userId:" + userId + "for " + userMeal);
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", userMeal.getId())
                .addValue("date_time", Timestamp.valueOf(userMeal.getDateTime()))
                .addValue("description", userMeal.getDescription())
                .addValue("calories", userMeal.getCalories())
                .addValue("user_id", userId);

        if (userMeal.isNew()) {
            Number newKey = insertUserMeal.executeAndReturnKey(map);
            userMeal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE MEALS SET date_time=:date_time, description=:description, calories=:calories WHERE id=:id", map);
        }

        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("delete id {} for user {}", id, userId);
        return jdbcTemplate.update("DELETE FROM MEALS WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        LOG.info("get meal{}", id);
        UserMeal userMeal = jdbcTemplate.queryForObject("SELECT * FROM meals WHERE id=?", this::mapUserMeal, id);
        if (userMeal.getUser().getId() != userId)
            throw new AccessViolationException("AssessViolation! bad userId:" + userId + "for " + userMeal);
        return userMeal;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        LOG.info("getAll for user {}", userId);
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? ORDER BY date_time", this::mapUserMeal, userId);
    }

    @Override
    public void deleteAll(int userId) {
        LOG.info("deleteAll for user {}", userId);
        jdbcTemplate.update("DELETE FROM meals WHERE user_id=?", userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        LOG.info("getBetween for user {}", userId);
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? AND( date_time>=?) AND (date_time<=?) ORDER BY date_time", this::mapUserMeal, userId,
                Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
    }

///////////////////////



/*
    @Override
    public User get(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, email, password, registered, enabled FROM users WHERE id=?",
                ROW_MAPPER, id);
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, email, password, registered, enabled FROM users WHERE email=?",
                ROW_MAPPER, email);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(
                "SELECT id, name, email, password, registered, enabled FROM users ORDER BY name, email",
                ROW_MAPPER);
    }*/


}
