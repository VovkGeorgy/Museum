package by.home.museum.repository;

import by.home.museum.entity.TourEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for tour entity
 */
public interface TourRepository extends CrudRepository<TourEntity, Long> {

}
