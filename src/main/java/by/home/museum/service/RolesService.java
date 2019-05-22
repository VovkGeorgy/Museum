package by.home.museum.service;

import by.home.museum.entity.RolesEntity;

/**
 * Roles service interface
 */
public interface RolesService {

    /**
     * Find entity by name
     *
     * @param name - name
     * @return entity
     */
    RolesEntity getByName(String name);

}
