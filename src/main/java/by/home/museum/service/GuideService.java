package by.home.museum.service;

import by.home.museum.entity.GuideEntity;

public interface GuideService {

    /**
     * Deletes a given entity.
     *
     * @param deleted - entity to delete
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(GuideEntity deleted);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Iterable<GuideEntity> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    GuideEntity findOne(Long id);

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param persisted - entity to save
     * @return the saved entity
     */
    GuideEntity save(GuideEntity persisted);

    /**
     * Find Guide entity in base by username
     * @param username - username of needed guide
     * @return guide entity
     */
    GuideEntity findByUsername(String username);
}
