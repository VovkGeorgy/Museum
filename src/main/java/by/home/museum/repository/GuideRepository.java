package by.home.museum.repository;

import by.home.museum.entity.GuideEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for guide entity
 */
public interface GuideRepository extends CrudRepository<GuideEntity, Long> {

    GuideEntity findByUsername (String username);
}
