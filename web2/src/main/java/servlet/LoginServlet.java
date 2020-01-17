package servlet;

import model.User;
import service.UserService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {


    UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserService.instance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!email.isEmpty()||!password.equals("")){
            User user = new User(email,password);
            if (userService.authUser(user)){
                resp.setStatus(200);
                doGet(req,resp);
            }
        }
        resp.setStatus(400);
        doGet(req,resp);
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.instance().getPage("authPage.html", new HashMap<>()));
        resp.setContentType("text/html;charset=utf-8");
    }
    }

