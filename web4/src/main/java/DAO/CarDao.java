package DAO;
import model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCar() {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();
        return cars;
    }

    public int getCarForBrand(String brand) {
        int count = 0;
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM Car Where brand= ' " + brand + "'").list();
        count = cars.size() + 1;
        transaction.commit();
        session.close();
        return count;
    }

    public void carAdd(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();

    }

    public void carDelete() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        transaction.commit();
        session.close();

    }

    public void carDeleteBuy(Car car) {
        Transaction transaction1 = session.beginTransaction();
        session.delete(car);
        transaction1.commit();
        session.close();
    }

    public Car getCarForLicensePlate(Car car) {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM Car Where licensePlate= ' " + car.getLicensePlate() + "'").list();
        transaction.commit();
        session.close();
        if (cars.size() > 0) {
            return cars.get(0);
        }
        return null;
    }

}





