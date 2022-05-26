package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/hibernate_db?autoReconnect=true&useSSL=false";
    private final static String USER = "user";
    private final static String PASSWORD = "123";
    private final static String DIALECT = "org.hibernate.dialect.MySQLDialect";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
//                        .setProperty("hibernate.connection.driver_class", DRIVER)
//                        .setProperty("hibernate.connection.url", URL)
//                        .setProperty("hibernate.connection.username", USER)
//                        .setProperty("hibernate.connection.password", PASSWORD)
//                        .setProperty("hibernate.dialect", DIALECT)
//                        .addAnnotatedClass(User.class);

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_db?useSSL=false");
                settings.put(Environment.USER, "user");
                settings.put(Environment.PASS, "123");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Connection true!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
//    Рассмотри параметры:
//    hibernate.connection.driver_class – здесь мы говорим какой драйвер использовать для работы с базой данных;
//    hibernate.connection.url – тут указываем URL к базе;
//    hibernate.connection.username – имя пользователя этой базы;
//    hibernate.connection.password – и его пароль;
//    hibernate.dialect – тут мы устанавливаем диалект текущей БД, он дает возможность использовать возможность генерации ключей, он автоматизирует всю эту работу;
//    hibernate.hbm2ddl.auto – тут статус работы JPA:
//    update – база будет просто обновлять свою структуру;
//    validate – проверяет структуру базы но не вносит изменения;
//    create – создает таблицы, но уничтожает предыдущие данные;
//    create-drop – создает таблицы в начале сеанса и удаляет их по окончанию сеанса.

    private static Transaction tx;

    public static void getUpdateTable(String sql) {
        sessionFactory = Util.getSessionFactory();
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            assert session != null;
            tx = session.beginTransaction();
            NativeQuery query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
//        try {
//            Session session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//            NativeQuery query = session.createSQLQuery(sql).addEntity(User.class);
//            query.executeUpdate();
//            session.getTransaction().commit();
//            session.close();
//        } catch (Exception ignored) {
//        }
    }
}
