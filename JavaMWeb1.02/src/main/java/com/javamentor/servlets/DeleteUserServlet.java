package com.javamentor.servlets;

import com.javamentor.models.User;
import com.javamentor.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteUsers")
public class DeleteUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] items = req.getParameterValues("Delete");
        List<User> users=userService.findAll();
        if (users == null)
            users = new ArrayList<>();
        for (User user : users) {
            for (String str : items) {
                if (str.equals(user.getId().toString())) {
                    userService.deleteUsers(user.getId());
                }
            }
        }

//        List<User> users = userService.findAll();
//        req.setAttribute("usersFromServer", users);
//        req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/users");
    }


}
