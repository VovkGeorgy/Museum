package by.home.museum.service.impl;

import by.home.museum.entity.VisitorEntity;
import by.home.museum.repository.VisitorRepository;
import by.home.museum.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository repository;

    @Autowired
    public VisitorServiceImpl(VisitorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(VisitorEntity deleted) {
        repository.delete(deleted);
    }

    @Override
    public Iterable<VisitorEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public VisitorEntity findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public VisitorEntity save(VisitorEntity persisted) {
        return repository.save(persisted);
    }

    @Override
    public VisitorEntity findByUsername(String username) {
        return repository.findByUsername(username);
    }
}