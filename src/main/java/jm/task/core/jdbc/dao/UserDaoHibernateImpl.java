package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE USERS (id serial primary key ,name text,lastName text, age INT);").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

        @Override
        public void dropUsersTable () {
            try(SessionFactory sessionFactory = Util.getSessionFactory()) {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.createSQLQuery("DROP TABLE USERS").executeUpdate();
                session.getTransaction().commit();
                session.close();
            }catch (Exception e) {
            }
        }

        @Override
        public void saveUser (String name, String lastName,byte age){
            User newUser = new User(name, lastName, age);
            try (SessionFactory sessionFactory = Util.getSessionFactory()) {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(newUser);
                session.getTransaction().commit();
                System.out.println("User с именем: " + name + " успешно добавлен в базу");
            }catch (Exception e){
            }
        }

        @Override
        public void removeUserById ( long id){
            try (SessionFactory sessionFactory = Util.getSessionFactory()) {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.createSQLQuery("DELETE FROM USERS WHERE id = " + id).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
            }
        }

        @Override
        public List<User> getAllUsers () {
            List<User> users = new ArrayList<>();
            try (SessionFactory sessionFactory = Util.getSessionFactory()) {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                users = session.createQuery("from User", User.class).list();;
                session.getTransaction().commit();
        } catch (Exception e) {
            }
            return users;
        }

        @Override
        public void cleanUsersTable () {
            try (SessionFactory sessionFactory = Util.getSessionFactory()) {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.createSQLQuery("Delete from USERS").executeUpdate();
                session.getTransaction().commit();
        } catch (Exception e) {
            }
        }
    }
