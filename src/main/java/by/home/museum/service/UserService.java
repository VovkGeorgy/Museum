package by.home.museum.service;

import by.home.museum.entity.User;
import by.home.museum.entity.UserRequest;

import java.util.List;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {
    void resetCredentials();

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    User save(UserRequest user);
}
