package servlet;

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

public class MoneyTransactionServlet extends HttpServlet {

    BankClientService bankClientService = new BankClientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getInstance().getPage("moneyTransactionPage.html", new HashMap<>()));
        resp.setContentType("text/html;charset=utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> pageVariables = new HashMap<>();
        String senderName = req.getParameter("senderName");
        String nameTo = req.getParameter("nameTo");
        String senderPass = req.getParameter("senderPass");
        String countToString = req.getParameter("count");
        Long count = 0L;

        if (!senderName.isEmpty() && !nameTo.isEmpty() && !senderPass.isEmpty() && !countToString.isEmpty()) {
            count = Long.parseLong(countToString);
            BankClient bankClient = new BankClient(senderName, senderPass, count);
            if (bankClientService.sendMoneyToClient(bankClient, nameTo, count)) {
                pageVariables.put("message", "The transaction was successful");
            } else  {
                pageVariables.put("message", "transaction rejected");
            }

            resp.setStatus(200);
            resp.getWriter().println(PageGenerator.getInstance().getPage("resultPage.html", pageVariables));
        } else {
            doGet(req, resp);
        }
    }
}
