package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.AccessViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vad on 31.03.2015 19:48.
 */
@Repository
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.getUser().getId() != userId)
            throw new AccessViolationException("AssessViolation! bad userId:" + userId + "for " + userMeal);
        if (userMeal.isNew()) {
            em.persist(userMeal);
        } else {
            em.merge(userMeal);
        }
        return userMeal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal userMeal=em.find(UserMeal.class, id);
        if (userMeal.getUser().getId() != userId)
            throw new AccessViolationException("AssessViolation! bad userId:" + userId + "for " + userMeal);
        return userMeal;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    @Transactional
    public void deleteAll(int userId) {
        em.createNamedQuery(UserMeal.DELETE_ALL).setParameter("userId", userId).executeUpdate();// != 0;
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        TypedQuery<UserMeal> namedQuery = em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class);
        namedQuery.setParameter("userId", userId);
        namedQuery.setParameter("after", startDate);
        namedQuery.setParameter("before", endDate);
        return namedQuery.getResultList();
    }
}
