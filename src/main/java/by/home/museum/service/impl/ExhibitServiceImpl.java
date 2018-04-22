package by.home.museum.service.impl;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.repository.ExhibitsRepository;
import by.home.museum.service.ExhibitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExhibitServiceImpl implements ExhibitService {

    @Autowired
    ExhibitsRepository repository;

    /**
     * Deletes a given entity.
     *
     * @param deleted - entity to delete
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete(ExhibitEntity deleted) {
        repository.delete(deleted);
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Override
    public Iterable<ExhibitEntity> findAll() {
        return (List<ExhibitEntity>) repository.findAll();
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public ExhibitEntity findOne(Long id) {
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
    public ExhibitEntity save(ExhibitEntity persisted) {
        return repository.save(persisted);
    }
}
