package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    public User save(User user) {
        LOG.info("save " + user);
        return repository.save(user);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        ExceptionUtil.check(repository.delete(id), id);
    }

    public User get(int id) throws NotFoundException {
        LOG.info("get " + id);
        return ExceptionUtil.check(repository.get(id), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        LOG.info("getByEmail " + email);
        return ExceptionUtil.check(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        LOG.info("getAll");
        return repository.getAll();
    }

    public void update(User user) throws NotFoundException {
        LOG.info("update " + user);
        ExceptionUtil.check(repository.save(user), user.getId());
    }
}
