package it.epicode.dao;

import it.epicode.entities.User;

public interface UserDao extends Dao {
    void addUser(User user);
}
