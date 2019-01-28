package by.home.museum.service.impl;

import by.home.museum.entity.TourEntity;
import by.home.museum.repository.TourRepository;
import by.home.museum.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Tour service class
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TourServiceImpl implements TourService {

    private final TourRepository repository;

    @Override
    public void delete(TourEntity deleted) {
        repository.delete(deleted);
    }

    @Override
    public List<TourEntity> findAll() {
        return (List<TourEntity>) repository.findAll();
    }

    @Override
    public TourEntity findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public TourEntity save(TourEntity persisted) {
        return repository.save(persisted);
    }
}
