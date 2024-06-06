package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl daoJDBC = new UserDaoHibernateImpl();
        daoJDBC.createUsersTable();

        daoJDBC.saveUser("Алексей", "Тестовый", (byte) 23);
        daoJDBC.saveUser("Алексей123", "Тестовый", (byte) 23);
        daoJDBC.saveUser("Алексей123123", "Тестовый", (byte) 23);
        daoJDBC.saveUser("Ivan", "Ivanov", (byte) 5);

        System.out.println(daoJDBC.getAllUsers().toString());

        daoJDBC.cleanUsersTable();
        daoJDBC.dropUsersTable();
    }
}
