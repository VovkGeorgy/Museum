package by.home.museum.service.impl;

import by.home.museum.entity.TourVisitorEntity;
import by.home.museum.entity.VisitorEntity;
import by.home.museum.repository.TourVisitorRepository;
import by.home.museum.repository.VisitorRepository;
import by.home.museum.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorRepository repository;

    @Autowired
    TourVisitorRepository TvRepository;

    @Override
    public void delete(VisitorEntity deleted) {
        repository.delete(deleted);
    }

    @Override
    public Iterable<VisitorEntity> findAll() {
        return (List<VisitorEntity>) repository.findAll();
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

    @Override
    public void addTourToVisitor(TourVisitorEntity tve) {
        TvRepository.save(tve);
    }

    @Override
    public void removeTourFromVisitor(TourVisitorEntity tve) {
        TvRepository.deleteTourOfVisitor(tve.getTourId(), tve.getVisitorId());
    }
}
