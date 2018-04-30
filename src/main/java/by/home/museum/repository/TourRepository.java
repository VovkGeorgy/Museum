package by.home.museum.repository;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.entity.TourEntity;
import by.home.museum.entity.TourExhibitEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TourRepository extends CrudRepository<TourEntity, Long> {

//    /**
//     * Deletes a given entity.
//     *
//     * @param deleted - entity to delete
//     * @throws IllegalArgumentException in case the given entity is {@literal null}.
//     */
//    void delete(TourEntity deleted);
//
//    /**
//     * Returns all instances of the type.
//     *
//     * @return all entities
//     */
//    List<TourEntity> findAll();
//
//    /**
//     * Retrieves an entity by its id.
//     *
//     * @param id must not be {@literal null}.
//     * @return the entity with the given id or {@literal null} if none found
//     * @throws IllegalArgumentException if {@code id} is {@literal null}
//     */
//    TourEntity findOne(Long id);
//
//    /**
//     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
//     * entity instance completely.
//     *
//     * @param persisted - entity to save
//     * @return the saved entity
//     */
//    TourEntity save(TourEntity persisted);
}
