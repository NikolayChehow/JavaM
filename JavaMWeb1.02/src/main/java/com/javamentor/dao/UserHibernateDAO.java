package com.javamentor.dao;
import com.javamentor.models.User;
import com.javamentor.util.DBHelper;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserHibernateDAO implements UsersDao {
    private Session session;

    private static UserHibernateDAO usersDaoJdbc;

    public static UserHibernateDAO getInstance() {
        if (usersDaoJdbc == null) {
            usersDaoJdbc = new UserHibernateDAO(DBHelper.getSessionFactory().openSession());
        }
        return usersDaoJdbc;
    }

    private UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public User findByFirstName(String firstName) {
        return (User) session.createQuery("FROM User Where firstName='" + firstName + "'").getSingleResult();


    }

    @Override
    public User find(Integer id) {

        Query query = session.createQuery("from User where id = '" + id + "' ");
        return (User) query.getSingleResult();

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





