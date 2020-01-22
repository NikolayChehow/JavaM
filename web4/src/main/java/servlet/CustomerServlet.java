package servlet;

import com.google.gson.Gson;
import model.Car;
import service.CarService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAllCars());
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(json);
        resp.setStatus(200);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String licensePlate = req.getParameter("licensePlate");
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        if (!licensePlate.isEmpty() || !brand.isEmpty() || !model.isEmpty()) {
            if (CarService.getInstance().carBuy(new Car(brand, model, licensePlate, 0L))) {
                resp.setStatus(200);
            }
        } else {
            resp.setStatus(403);
        }
        doGet(req, resp);
    }
}

