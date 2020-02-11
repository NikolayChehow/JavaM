package com.javamentor.dao;

import com.javamentor.models.User;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UsersDao {
    private Session session;

//    private static UserHibernateDAO usersDaoJdbc;
//
//    public static UserHibernateDAO getInstance() {
//        if (usersDaoJdbc == null) {
//            usersDaoJdbc = new UserHibernateDAO(DBHelper.getSessionFactory().openSession());
//        }
//        return usersDaoJdbc;
//    }

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public User findByEmail(String email) {
        return (User) session.createQuery("FROM User Where email='" + email + "'").getSingleResult();
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
        Query query = session.createQuery("update User set firstName=:firstName, lastName=:lastName, email=:email, password=:password , nameRole=:nameRole where id =:id");
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());
        query.setParameter("nameRole", user.getNameRole());
        query.setParameter("id", user.getId());
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
    }


    @Override
    public void delete(Integer id) {

        Transaction transaction = session.beginTransaction();
        session.delete(find(id));
        transaction.commit();
        session.close();


    }

    @Override
    public List<User> findAll() {
        List<User> result = session.createQuery("from User").list();
        return result;
    }
}





