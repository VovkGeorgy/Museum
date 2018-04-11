package by.home.museum.repository;

import by.home.museum.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  Authority findByName(String name);
}
