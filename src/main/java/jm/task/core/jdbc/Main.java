package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
//        Util.getSessionFactory();

        UserDao userDao = new UserDaoHibernateImpl();

//        userDao.createUsersTable();
        userDao.saveUser("Tom","Lite",(byte)45);
        userDao.saveUser("Nik","White",(byte)12);
        userDao.saveUser("Elsa","Clear",(byte)22);
        userDao.saveUser("Bob","Bomber",(byte)35);
        System.out.println(userDao.getAllUsers());
//        userDao.removeUserById(2);
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
        Util.getSessionFactory().close();
    }
}
