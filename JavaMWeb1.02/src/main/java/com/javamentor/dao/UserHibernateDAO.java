package com.javamentor.dao;
import com.javamentor.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserHibernateDAO implements UsersDao{
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public User findByFirstName(String firstName) {
        return  (User) session.createQuery("FROM User Where firstName='" + firstName + "'").list().get(0);


    }

    @Override
    public Optional<User> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(User model) {
        Transaction transaction = session.beginTransaction();
        session.save(model);
        transaction.commit();
        session.close();

    }

    @Override
    public void update(User user) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE User SET lastName = '" + user.getLastName() + "' WHERE firstName ='" + user.getFirstName() + "'").executeUpdate();
        transaction.commit();
        session.close();
    }





    @Override
    public void delete(String name) {

        Transaction transaction = session.beginTransaction();
        session.delete(findByFirstName(name));
        transaction.commit();
        session.close();


    }

    @Override
    public List<User> findAll() {
        List<User> result = session.createQuery("from User").list();
        return result;
    }
}
//
//    private Session session;
//
//    public CarDao(Session session) {
//        this.session = session;
//    }
//
//    public List<Car> getAllCar() {
//        Transaction transaction = session.beginTransaction();
//        List<Car> cars = session.createQuery("FROM Car").list();
//        transaction.commit();
//        session.close();
//        return cars;
//    }
//
//    public int getCarForBrand(String brand) {
//        int count = 0;
//        Transaction transaction = session.beginTransaction();
//        List<Car> cars = session.createQuery("FROM Car Where brand= ' " + brand + "'").list();
//        count = cars.size() + 1;
//        transaction.commit();
//        session.close();
//        return count;
//    }
//
//    public void carAdd(Car car) {
//        Transaction transaction = session.beginTransaction();
//        session.save(car);
//        transaction.commit();
//        session.close();
//
//    }
//
//    public void carDelete() {
//        Transaction transaction = session.beginTransaction();
//        session.createQuery("DELETE FROM Car").executeUpdate();
//        transaction.commit();
//        session.close();
//
//    }
//
//    public void carDeleteBuy(Car car) {
//        Transaction transaction1 = session.beginTransaction();
//        session.delete(car);
//        transaction1.commit();
//        session.close();
//    }
//
//    public Car getCarForLicensePlate(Car car) {
//        Transaction transaction = session.beginTransaction();
//        List<Car> cars = session.createQuery("FROM Car Where licensePlate= '" + car.getLicensePlate() + "'").list();
//        transaction.commit();
//        session.close();
//        if (cars.size() > 0) {
//            return cars.get(0);
//        }
//        return null;
//    }
//
//}





