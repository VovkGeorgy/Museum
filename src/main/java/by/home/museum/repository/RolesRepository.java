package by.home.museum.repository;

import by.home.museum.entity.RolesEntity;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<RolesEntity, Long> {

    RolesEntity findByName(String roleName);
}
