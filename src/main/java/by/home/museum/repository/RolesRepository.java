package by.home.museum.repository;

import by.home.museum.entity.RolesEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for roles entity
 */
public interface RolesRepository extends CrudRepository<RolesEntity, Long> {

    RolesEntity findByName(String roleName);
}
