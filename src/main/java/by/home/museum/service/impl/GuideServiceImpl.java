package by.home.museum.service.impl;

import by.home.museum.entity.GuideEntity;
import by.home.museum.repository.GuideRepository;
import by.home.museum.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    GuideRepository repository;

    /**
     * Deletes a given entity.
     *
     * @param deleted - entity to delete
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete(GuideEntity deleted) {
        repository.delete(deleted);
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Override
    public List<GuideEntity> findAll() {
        return (List<GuideEntity>) repository.findAll();
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public GuideEntity findOne(Long id) {
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
    public GuideEntity save(GuideEntity persisted) {
        return repository.save(persisted);
    }
}
