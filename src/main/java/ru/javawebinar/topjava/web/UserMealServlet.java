package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vad on 01.03.2015.
 */
public class UserMealServlet extends HttpServlet {
    
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServlet.class);
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to userMeal");

        request.getRequestDispatcher("/userMeal.jsp").forward(request, response);
        //response.sendRedirect("userList.jsp");
    }
}
