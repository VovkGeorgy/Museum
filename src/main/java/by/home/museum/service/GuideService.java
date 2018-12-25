package by.home.museum.service;

import by.home.museum.entity.GuideEntity;

public interface GuideService extends EntityService<GuideEntity> {

    /**
     * Find Guide entity in base by username
     *
     * @param username - username of needed guide
     * @return guide entity
     */
    GuideEntity findByUsername(String username);
}
