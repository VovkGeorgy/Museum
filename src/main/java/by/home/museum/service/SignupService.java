package by.home.museum.service;

import by.home.museum.entity.UsersEntity;

/**
 * SignUp service interface
 */
public interface SignupService {

    /**
     * Saves a given entity.
     *
     * @param user - entity to save
     * @return the saved entity
     */
    UsersEntity addUser(UsersEntity user);


    /**
     * Deletes a given entity.
     *
     * @param user - entity to delete
     */
    void delUser(UsersEntity user);
}
