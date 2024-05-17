package it.epicode.booking.dao;

import it.epicode.booking.entities.User;
import it.epicode.booking.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CRUDDao<User> {

    @Autowired
    UserRepository user;


    @Override
    public void create(User e ) {
        user.save(e);
    }

    @Override
    public void update(User e) {
        user.save(e);
    }

    @Override
    public void delete(Long id) {
        user.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return user.findById(id).orElse(null);
    }
}
