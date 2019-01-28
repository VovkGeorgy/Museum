package by.home.museum.repository;

import by.home.museum.entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for users entity
 */
public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

    /**
     * Find entity by username
     *
     * @param username - username
     * @return entity
     */
    UsersEntity findByUsername(String username);
}
