package by.home.museum.service;

import by.home.museum.dto.TourGuideDto;
import by.home.museum.entity.GuideEntity;

/**
 * Guide service interface
 */
public interface GuideService extends EntityService<GuideEntity> {

    /**
     * Find Guide entity in base by username
     *
     * @param username - username of needed guide
     * @return guide entity
     */
    GuideEntity findByUsername(String username);

    /**
     * Update persistent guide entity and it user entity
     *
     * @param guide with new data
     * @return uodated guide
     */
    GuideEntity updateGuide(GuideEntity guide);

    /**
     * Remove tours from guide
     *
     * @param tgd tours to remove
     */
    GuideEntity removeTours(TourGuideDto tgd);

    /**
     * Add tours to guide
     *
     * @param tgd tours - guide - dto
     * @return updated gudie
     */
    GuideEntity addTours(TourGuideDto tgd);
}
