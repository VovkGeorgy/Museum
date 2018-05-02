package by.home.museum.service;

import by.home.museum.entity.UsersEntity;

public interface UserService {

    /**
     * Find entity by username
     *
     * @param username - username
     * @return entity
     */
    UsersEntity findByUsername(String username);

}
