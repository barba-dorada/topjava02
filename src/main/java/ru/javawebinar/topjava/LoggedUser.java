package ru.javawebinar.topjava;

/**
 * Created by vad on 06.03.2015.
 * DTO
 */

public class LoggedUser {
    protected int id;
    protected Set<Role> poles;
    protected boolean enabled;
    public int getId(){
        return id;
    }
}
