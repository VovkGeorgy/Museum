package by.home.museum.service.impl;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.repository.ExhibitsRepository;
import by.home.museum.service.ExhibitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Exhibit service class
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExhibitServiceImpl implements ExhibitService {

    private final ExhibitsRepository repository;

    @Override
    public void delete(ExhibitEntity deleted) {
        repository.delete(deleted);
    }

    @Override
    public Iterable<ExhibitEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public ExhibitEntity findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public ExhibitEntity save(ExhibitEntity persisted) {
        return repository.save(persisted);
    }
}
