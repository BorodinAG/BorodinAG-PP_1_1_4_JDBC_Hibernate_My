package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.query.NativeQuery;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS User (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "firstname VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age TINYINT NOT NULL)";
        Util.getUpdateTable(sqlCreate);
    }

    @Override
    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF EXISTS User";
        Util.getUpdateTable(sqlDrop);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            userList = session.createSQLQuery("SELECT * FROM user").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sqlClean = "TRUNCATE TABLE User";
        Util.getUpdateTable(sqlClean);
    }
}
