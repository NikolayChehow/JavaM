package com.javamentor.servlets;

import com.javamentor.models.User;
import com.javamentor.service.UserService;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
//        session.setAttribute("user",name);
//        req.getServletContext().getRequestDispatcher("/home").forward(req,resp);
//    } else resp.sendRedirect(req.getContextPath()+"/login");


        User user = userService.validateClient(email, password);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else if (user.getNameRole().equals("user")) {
            session.setAttribute("user", email);
            resp.sendRedirect(req.getContextPath() + "/user/home");
        } else if (user.getNameRole().equals("admin")) {
            session.setAttribute("admin", email);
            resp.sendRedirect(req.getContextPath() + "/admin/admin");
        }
    }
}





