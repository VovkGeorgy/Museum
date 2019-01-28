package by.home.museum.service;

/**
 * Entity service interface
 */
public interface EntityService<T> {
    /**
     * Deletes a given entity.
     *
     * @param deleted - entity to delete
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T deleted);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Iterable<T> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    T findOne(Long id);

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param persisted - entity to save
     * @return the saved entity
     */
    T save(T persisted);
}
