package by.home.museum.service.impl;

import by.home.museum.entity.VisitorEntity;
import by.home.museum.repository.VisitorRepository;
import by.home.museum.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorRepository repository;

    /**
     * Deletes a given entity.
     *
     * @param deleted - entity to delete
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete(VisitorEntity deleted) {
        repository.delete(deleted);
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Override
    public Iterable<VisitorEntity> findAll() {
        return (List<VisitorEntity>) repository.findAll();
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public VisitorEntity findOne(Long id) {
        return repository.findOne(id);
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param persisted - entity to save
     * @return the saved entity
     */
    @Override
    public VisitorEntity save(VisitorEntity persisted) {
        return repository.save(persisted);
    }
}
