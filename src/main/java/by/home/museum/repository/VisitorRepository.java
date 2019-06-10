package by.home.museum.repository;

import by.home.museum.entity.VisitorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * JPA repository for visitor entity
 */
public interface VisitorRepository extends CrudRepository<VisitorEntity, Long> {

    VisitorEntity findByUsername(String username);

    List<VisitorEntity> findAllByOrderByFio();
}
