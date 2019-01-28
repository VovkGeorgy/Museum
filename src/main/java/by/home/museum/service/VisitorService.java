package by.home.museum.service;

import by.home.museum.entity.VisitorEntity;

/**
 * Visitor service interface
 */
public interface VisitorService extends EntityService<VisitorEntity> {

    VisitorEntity findByUsername(String username);

}
