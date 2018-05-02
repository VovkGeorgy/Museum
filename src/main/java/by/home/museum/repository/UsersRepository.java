package by.home.museum.repository;

import by.home.museum.entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UsersEntity, Long> {
	
	UsersEntity findByUsername(String username);
}
