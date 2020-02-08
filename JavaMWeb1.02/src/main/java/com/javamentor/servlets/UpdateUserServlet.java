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

@WebServlet("/updateUsers")
public class UpdateUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();
    Integer id = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.id=Integer.parseInt(req.getParameter("id"));
        User user = userService.find(id);
        List<User> users = new ArrayList<>();
        users.add(user);
        req.setAttribute("usersFromDB", users);
        req.getServletContext().getRequestDispatcher("/jsp/update.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(id, firstName, lastName, email, password);
        userService.updateUsers(user);
//        List<User> users = userService.findAll();
//        req.setAttribute("usersFromServer", users);
//        req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/users");

    }


}
