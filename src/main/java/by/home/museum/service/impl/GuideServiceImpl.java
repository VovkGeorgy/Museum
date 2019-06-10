package by.home.museum.service.impl;

import by.home.museum.entity.GuideEntity;
import by.home.museum.repository.GuideRepository;
import by.home.museum.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Guide service class
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuideServiceImpl implements GuideService {

    private final GuideRepository repository;

    @Override
    public void delete(GuideEntity deleted) {
        repository.delete(deleted);
    }

    @Override
    public List<GuideEntity> findAll() {
        return repository.findAllByOrderByFio();
    }

    @Override
    public GuideEntity findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public GuideEntity save(GuideEntity persisted) {
        return repository.save(persisted);
    }

    @Override
    public GuideEntity findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
