package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    static String url = "jdbc:mysql://localhost:3306/testdb";
    static String user = "root";
    static String password = "mysql";


    public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url",url)
                .setProperty("hibernate.connection.username",user)
                .setProperty("hibernate.connection.password",password)
                .setProperty("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect")
                .setProperty("hibernate.show_sql","true")
                .setProperty("hibernate.hbm2ddl.auto","")
                .addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
}
