package by.home.museum.repository;

import by.home.museum.entity.VisitorEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for visitor entity
 */
public interface VisitorRepository extends CrudRepository<VisitorEntity, Long> {

    VisitorEntity findByUsername(String username);
}
