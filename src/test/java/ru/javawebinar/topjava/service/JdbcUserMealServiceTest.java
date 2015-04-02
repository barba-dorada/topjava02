package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;

/**
 * Created by vad on 01.04.2015 11:47.
 */

@ActiveProfiles({"postgres","jdbc"})
public class JdbcUserMealServiceTest extends UserMealServiceTest {
}
