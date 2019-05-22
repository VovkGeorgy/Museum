package by.home.museum.service;

import by.home.museum.entity.UsersEntity;

/**
 * User service interface
 */
public interface UserService {

    /**
     * Find user entity by username
     *
     * @param username - username
     * @return deleted entity
     */
    UsersEntity findByUsername(String username);

}
