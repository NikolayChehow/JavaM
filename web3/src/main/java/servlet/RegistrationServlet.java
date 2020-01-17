package servlet;

import exception.DBException;
import model.BankClient;
import service.BankClientService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getInstance().getPage("registrationPage.html", new HashMap<>()));
        resp.setContentType("text/html;charset=utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String moneyToString = req.getParameter("money");
        Long money;


        if (!name.isEmpty() && !password.isEmpty() && !moneyToString.isEmpty()) {
            money = Long.parseLong(moneyToString);
            BankClient bankClient = new BankClient(name, password, money);
            try {
                if (new BankClientService().addClient(bankClient)) {
                    pageVariables.put("message", "Add client successful");
                } else {
                    pageVariables.put("message", "Client not add");
                }
            } catch (DBException ignored) {
            }
            resp.setStatus(200);
            resp.getWriter().println(PageGenerator.getInstance().getPage("resultPage.html", pageVariables));
        } else {
            doGet(req, resp);
        }
    }
}
