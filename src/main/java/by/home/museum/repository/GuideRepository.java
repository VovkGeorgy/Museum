package by.home.museum.repository;

import by.home.museum.entity.GuideEntity;
import org.springframework.data.repository.CrudRepository;

public interface GuideRepository extends CrudRepository<GuideEntity, Long> {

    GuideEntity findByFio(String fio);
}
