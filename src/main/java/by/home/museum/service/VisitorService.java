package by.home.museum.service;

import by.home.museum.entity.VisitorEntity;

public interface VisitorService extends EntityService<VisitorEntity> {

    VisitorEntity findByUsername(String username);

//    void addTourToVisitor(TourVisitorEntity tve);

//    void removeTourFromVisitor(TourVisitorEntity tve);
}
