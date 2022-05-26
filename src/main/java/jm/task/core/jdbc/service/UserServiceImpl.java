package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.cleanUsersTable();
    }
}
