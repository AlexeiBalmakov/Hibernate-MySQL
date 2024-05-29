package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement st = connection.createStatement();) {
            st.executeQuery("CREATE TABLE USERS (id serial primary key ,name text,lastName text, age INT);");
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        try (Statement st = connection.createStatement();) {
            st.executeQuery("Drop TABLE USERS;");
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO users (name, lastname,age) VALUES (?, ?,?)")) {
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            st.executeUpdate();
            System.out.println("User с именем: " + name + " успешно добавлен в базу");
        } catch (SQLException e) {
        }
    }

    public void removeUserById(long id) {
        try (Statement st = connection.createStatement();) {
            st.executeUpdate("Delete from USERS where id = " + id);
        } catch (SQLException e) {
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Statement st = connection.createStatement();) {
            ResultSet usersList1 = st.executeQuery("select * from users;");
            while(usersList1.next()) {
                User user = new User();
                user.setId(usersList1.getLong("id"));
                user.setName(usersList1.getString("name"));
                user.setLastName(usersList1.getString("lastName"));
                user.setAge(usersList1.getByte("age"));
                usersList.add(user);
            }
        } catch (SQLException e) {
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (Statement st = connection.createStatement();) {
            st.executeUpdate("Delete from USERS ");
        } catch (SQLException e) {
        }
    }
}
