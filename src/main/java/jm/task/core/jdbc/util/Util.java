package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String password = "postgres";

    public static Connection getConnection(){
        Connection connection;
        try {
           connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return connection;
    }

    public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url",url)
                .setProperty("hibernate.connection.username",user)
                .setProperty("hibernate.connection.password",password)
                .setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.show_sql","true")
                .setProperty("hibernate.hbm2ddl.auto","")
                .addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
}
