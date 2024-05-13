package it.epicode.dao.implementation;

import it.epicode.dao.interfaces.Dao;
import it.epicode.dao.interfaces.UserDao;
import it.epicode.entities.User;

public class JpaUserDao extends JpaDao<User> implements Dao<User>, UserDao {
    public JpaUserDao() {super(User.class);}
}
