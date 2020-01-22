package service;

import DAO.CarDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }


    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCar();
    }

    public int getCarForBrand(String brand) {
        return new CarDao(sessionFactory.openSession()).getCarForBrand(brand);
    }

    public void carAdd(Car car) {
        new CarDao(sessionFactory.openSession()).carAdd(car);

    }

    public void carDelete() {
        new CarDao(sessionFactory.openSession()).carDelete();

    }

    public void carDeleteBuy(Car car) {
        new CarDao(sessionFactory.openSession()).carDeleteBuy(car);

    }

    public boolean carBuy(Car car) {
        CarDao dao = new CarDao(sessionFactory.openSession());
        CarDao dao1 = new CarDao(sessionFactory.openSession());

//        Car carTable = dao.getCarForLicensePlate(car);
//        if (car.equals(carTable)) {
//                DailyReport.instance().setEarnings(carTable.getPrice());
//                DailyReport.instance().setSoldCars(1L);
//                dao.carDeleteBuy(carTable);
//                return true;
//            }
//        return false;
        List<Car> cars = dao.getAllCar();
        for (Car carTable : cars) {
            if (car.equals(carTable)) {
                DailyReport.instance().setEarnings(carTable.getPrice());
                DailyReport.instance().setSoldCars(1L);
                dao1.carDeleteBuy(carTable);
                return true;
            }
        }
        return false;

    }
}


