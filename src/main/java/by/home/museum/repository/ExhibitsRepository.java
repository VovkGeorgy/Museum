package by.home.museum.repository;

import by.home.museum.entity.ExhibitEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * JPA repository for exhibit entity
 */
public interface ExhibitsRepository extends CrudRepository<ExhibitEntity, Long> {

    List<ExhibitEntity> findAllByOrderByTitle();
}
