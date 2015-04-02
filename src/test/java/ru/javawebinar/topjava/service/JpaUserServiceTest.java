package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;

/**
 * Created by vad on 01.04.2015 11:09.
 */

@ActiveProfiles({"postgres", "jpa"})
public class JpaUserServiceTest extends UserServiceTest {

}
