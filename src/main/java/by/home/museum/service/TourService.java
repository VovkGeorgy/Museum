package by.home.museum.service;

import by.home.museum.entity.TourEntity;

import java.util.List;

/**
 * Tour service interface
 */
public interface TourService extends EntityService<TourEntity> {

    /**
     * Get all tours without Guide
     *
     * @return list of tours
     */
    List<TourEntity> getAllByGuideEntityIsNull();
}
